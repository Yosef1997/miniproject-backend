package com.tickitz.backend.event.service.impl;

import com.tickitz.backend.event.dto.CreateEventRequestDto;
import com.tickitz.backend.event.dto.EventResponseDto;
import com.tickitz.backend.event.dto.UpdateEventRequestDto;
import com.tickitz.backend.event.entity.Event;
import com.tickitz.backend.event.repository.EventRepository;
import com.tickitz.backend.event.repository.EventSpecifications;
import com.tickitz.backend.event.service.EventService;
import com.tickitz.backend.exceptions.applicationException.ApplicationException;
import com.tickitz.backend.promotion.dto.CreatePromoRequestDto;
import com.tickitz.backend.promotion.dto.PromoResponseDto;
import com.tickitz.backend.promotion.service.PromotionService;
import com.tickitz.backend.ticket.dto.CreateTicketRequestDto;
import com.tickitz.backend.ticket.dto.TicketResponseDto;
import com.tickitz.backend.ticket.service.TicketService;
import com.tickitz.backend.users.entity.Users;
import com.tickitz.backend.users.repository.UsersRepository;
import lombok.extern.java.Log;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Service
@Log
public class EventServiceImpl implements EventService {
  private final EventRepository eventRepository;
  private final UsersRepository usersRepository;
  private final TicketService ticketService;
  private final PromotionService promotionService;

  public EventServiceImpl(EventRepository eventRepository, UsersRepository usersRepository, TicketService ticketService, PromotionService promotionService) {
    this.eventRepository = eventRepository;
    this.usersRepository = usersRepository;
    this.ticketService = ticketService;
    this.promotionService = promotionService;
  }

//  @Override
//  public List<Event> getAllEvents() {
//    return eventRepository.findAll();
//  }

  @Override
  public Page<Event> getAllEvents(Pageable pageable, String eventName, String location, String category) {
    Specification<Event> specification = Specification.where(EventSpecifications.byEventName(eventName))
            .and(EventSpecifications.byLocation(location))
            .and(EventSpecifications.byCategory(category));
    return eventRepository.findAll(specification, pageable);

  }

  @Override
  public EventResponseDto getDetailEvent(Long id) {
    Event detail = eventRepository.findById(id).orElseThrow(() -> new ApplicationException("Event not exists"));
    EventResponseDto response = new EventResponseDto();
    response.setId(detail.getId());
    response.setEventName(detail.getEventName());
    response.setEventImage(detail.getEventImage());
    response.setCategory(detail.getCategory());
    response.setLocation(detail.getLocation());
    response.setVenue(detail.getVenue());
    response.setDescription(detail.getDescription());
    response.setDate(detail.getDate());
    response.setStartTime(detail.getStartTime());
    response.setEndTime(detail.getEndTime());
    response.setUserId(detail.getUser().getId());
    response.setTickets(ticketService.getAllTickets(detail.getId()));
    response.setPromotions(promotionService.getAllPromotions(detail.getId()));
    return response;
  }

  @Override
  public EventResponseDto createEvent(CreateEventRequestDto createEventRequestDto) {
    Users user = usersRepository.findById(createEventRequestDto.getUserId()).orElseThrow(() -> new ApplicationException("User not found"));
    if (!user.getRole().name().equals(Users.Role.ORGANIZER.name())) {
      throw new ApplicationException("Only Organizer can create an event");
    }

    Event event = new Event();
    event.setEventName(createEventRequestDto.getEventName());
    event.setEventImage(createEventRequestDto.getEventImage());
    event.setCategory(createEventRequestDto.getCategory());
    event.setDate(Instant.parse(createEventRequestDto.getDate()));
    event.setStartTime(Instant.parse(createEventRequestDto.getStartTime()));
    event.setEndTime(Instant.parse(createEventRequestDto.getEndTime()));
    event.setLocation(createEventRequestDto.getLocation());
    event.setVenue(createEventRequestDto.getVenue());
    event.setDescription(createEventRequestDto.getDescription());
    event.setUser(user);
    Event savedEvent = eventRepository.save(event);


    List<TicketResponseDto> tickets = new ArrayList<>();
    for (CreateTicketRequestDto ticketType : createEventRequestDto.getTickets()) {
      ticketType.setEventId(savedEvent.getId());
      TicketResponseDto newTicket = ticketService.createTicket(ticketType);
//      Ticket existingTicket = ticketRepository.findByName(eventType.getName()).orElseGet(() -> {
//        Ticket newTicket = new Ticket();
//        newTicket.setName(eventType.getName());
//        newTicket.setSeats(eventType.getSeats());
//        newTicket.setPrice(eventType.getPrice());
//        newTicket.setEventId(savedEvent.getId());
//        return ticketRepository.save(newTicket);
//      });
      tickets.add(newTicket);
    }

    List<PromoResponseDto> promotions = new ArrayList<>();
    for (CreatePromoRequestDto promotion : createEventRequestDto.getPromotions()) {
      promotion.setEventId(savedEvent.getId());
      PromoResponseDto newPromo = promotionService.createPromotion(promotion);
//      Promotion existingPromotion = promotionRepository.findByName(promotion.getName()).orElseGet(() -> {
//        Promotion newPromotion = new Promotion();
//        newPromotion.setName(promotion.getName());
//        newPromotion.setType(promotion.getType());
//        newPromotion.setUsageLimit(promotion.getUsageLimit());
//        newPromotion.setDiscount(promotion.getDiscount());
//        newPromotion.setExpiredDate(promotion.getExpiredDate());
//        newPromotion.setEventId(savedEvent.getId());
//        return promotionRepository.save(newPromotion);
//      });
      promotions.add(newPromo);
    }

    EventResponseDto response = new EventResponseDto();
    response.setId(savedEvent.getId());
    response.setEventName(savedEvent.getEventName());
    response.setEventImage(savedEvent.getEventImage());
    response.setCategory(savedEvent.getCategory());
    response.setDate(savedEvent.getDate());
    response.setStartTime(savedEvent.getStartTime());
    response.setEndTime(savedEvent.getEndTime());
    response.setLocation(savedEvent.getLocation());
    response.setVenue(savedEvent.getVenue());
    response.setDescription(savedEvent.getDescription());
    response.setTickets(ticketService.getAllTickets(savedEvent.getId()));
    response.setPromotions(promotionService.getAllPromotions(savedEvent.getId()));
    return response;
  }

  @Override
  public EventResponseDto updateEvent(UpdateEventRequestDto updateEventRequestDto) {
    Users user = usersRepository.findById(updateEventRequestDto.getUserId()).orElseThrow(() -> new ApplicationException("User not found"));
    if (!user.getRole().name().equals(Users.Role.ORGANIZER.name())) {
      throw new ApplicationException("Only Organizer can create an event");
    }
    Event existsEvent = eventRepository.findById(updateEventRequestDto.getId()).orElseThrow(() -> new ApplicationException("Event with id " + updateEventRequestDto.getId() + " not exists"));
    existsEvent.setEventName(updateEventRequestDto.getEventName());
    existsEvent.setEventImage(updateEventRequestDto.getEventImage());
    existsEvent.setCategory(updateEventRequestDto.getCategory());
    existsEvent.setLocation(updateEventRequestDto.getLocation());
    existsEvent.setVenue(updateEventRequestDto.getVenue());
    existsEvent.setDescription(updateEventRequestDto.getDescription());
    existsEvent.setDate(Instant.parse(updateEventRequestDto.getDate()));
    existsEvent.setStartTime(Instant.parse(updateEventRequestDto.getStartTime()));
    existsEvent.setEndTime(Instant.parse(updateEventRequestDto.getEndTime()));
    Event updated = eventRepository.save(existsEvent);

    EventResponseDto response = new EventResponseDto();
    response.setId(updated.getId());
    response.setEventName(updated.getEventName());
    response.setEventImage(updated.getEventImage());
    response.setCategory(updated.getCategory());
    response.setLocation(updated.getLocation());
    response.setVenue(updated.getVenue());
    response.setDescription(updated.getDescription());
    response.setDate(updated.getDate());
    response.setStartTime(updated.getStartTime());
    response.setEndTime(updated.getEndTime());
    response.setTickets(ticketService.getAllTickets(updated.getId()));
    response.setPromotions(promotionService.getAllPromotions(updated.getId()));
    return response;
  }

  @Override
  public String deleteEvent(Long eventId) {
    eventRepository.findById(eventId).orElseThrow(() -> new ApplicationException("Event not exists"));
    eventRepository.deleteById(eventId);
    return "Delete event success";
  }
}
