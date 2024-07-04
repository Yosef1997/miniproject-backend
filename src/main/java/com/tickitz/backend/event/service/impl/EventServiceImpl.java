package com.tickitz.backend.event.service.impl;

import com.tickitz.backend.event.dto.RequestEventDto;
import com.tickitz.backend.event.entity.Event;
import com.tickitz.backend.event.repository.EventRepository;
import com.tickitz.backend.event.service.EventService;
import com.tickitz.backend.exceptions.applicationException.ApplicationException;
import com.tickitz.backend.promotion.entity.Promotion;
import com.tickitz.backend.promotion.repository.PromotionRepository;
import com.tickitz.backend.ticket.entity.Ticket;
import com.tickitz.backend.ticket.repository.TicketRepository;
import com.tickitz.backend.users.entity.Users;
import com.tickitz.backend.users.repository.UsersRepository;
import lombok.extern.java.Log;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Log
public class EventServiceImpl implements EventService {
  private final EventRepository eventRepository;
  private final UsersRepository usersRepository;
  private final TicketRepository ticketRepository;
  private final PromotionRepository promotionRepository;
  public EventServiceImpl(EventRepository eventRepository, UsersRepository usersRepository, TicketRepository ticketRepository, PromotionRepository promotionRepository) {
    this.eventRepository = eventRepository;
    this.usersRepository = usersRepository;
    this.ticketRepository = ticketRepository;
    this.promotionRepository = promotionRepository;
  }

  @Override
  public List<Event> getAllEvents() {
    return eventRepository.findAll();
  }

  @Override
  public Optional<Event> getDetailEvent(Long id) {
    return eventRepository.findById(id);
  }

  @Override
  public Event createEvent(RequestEventDto requestEventDto) {
//    Users user = usersRepository.findById(requestEventDto.getUserId()).orElseThrow(() -> new ApplicationException("User not found"));
//    if (!user.getRole().name().equals(Users.Role.ORGANIZER.name())) {
//      throw new ApplicationException("Only Organizer can create an event");
//    }
//    Optional<Event> eventExists = eventRepository.findByEventName(requestEventDto.getEventImage());
//
//    if (eventExists.isPresent()){
//      throw new ApplicationException("Event name already exists");
//    }
//
//    Event event = new Event();
//    event.setEventName(requestEventDto.getEventName());
//    event.setEventImage(requestEventDto.getEventImage());
//    event.setCategory(requestEventDto.getCategory());
//    event.setDate(Instant.parse(requestEventDto.getDate()));
//    event.setStartTime(Instant.parse(requestEventDto.getStartTime()));
//    event.setEndTime(Instant.parse(requestEventDto.getEndTime()));
//    event.setLocation(requestEventDto.getLocation());
//    event.setVenue(requestEventDto.getVenue());
//    event.setDescription(requestEventDto.getDescription());
//    event.setUser(user);
//    Event savedEvent = eventRepository.save(event);
//
//
//    List<Ticket> tickets = new ArrayList<>();
//    for (Ticket eventType : requestEventDto.getTickets()) {
//      Ticket existingTicket = ticketRepository.findByName(eventType.getName())
//              .orElseGet(() -> {
//                Ticket newTicket = new Ticket();
//                newTicket.setName(eventType.getName());
//                newTicket.setSeats(eventType.getSeats());
//                newTicket.setPrice(eventType.getPrice());
//                newTicket.setEventId(savedEvent.getId());
//                return ticketRepository.save(newTicket);
//              });
//      tickets.add(existingTicket);
//    }
//
//    List<Promotion> promotions = new ArrayList<>();
//    for (Promotion promotion : requestEventDto.getPromotions()) {
//      Promotion existingPromotion = promotionRepository.findByName(promotion.getName())
//              .orElseGet(() -> {
//                Promotion newPromotion = new Promotion();
//                newPromotion.setName(promotion.getName());
//                newPromotion.setType(promotion.getType());
//                newPromotion.setUsageLimit(promotion.getUsageLimit());
//                newPromotion.setDiscount(promotion.getDiscount());
//                newPromotion.setExpiredDate(promotion.getExpiredDate());
//                newPromotion.setEventId(savedEvent.getId());
//                return promotionRepository.save(newPromotion);
//              });
//      promotions.add(existingPromotion);
//    }
//
//    Event response = new Event();
//    response.setId(savedEvent.getId());
//    response.setEventName(savedEvent.getEventName());
//    response.setEventImage(savedEvent.getEventImage());
//    response.setCategory(savedEvent.getCategory());
//    response.setDate(savedEvent.getDate());
//    response.setStartTime(savedEvent.getStartTime());
//    response.setEndTime(savedEvent.getEndTime());
//    response.setLocation(savedEvent.getLocation());
//    response.setVenue(savedEvent.getVenue());
//    response.setDescription(savedEvent.getDescription());
//    response.setTickets(tickets);
//    response.setPromotions(promotions);
//    response.setCreatedAt(savedEvent.getCreatedAt());
//    response.setUpdatedAt(savedEvent.getUpdatedAt());
//    response.setDeletedAt(savedEvent.getDeletedAt());
    return null;
  }

  @Override
  public RequestEventDto updateEvent(RequestEventDto requestEventDto) {
    Users user = usersRepository.findById(requestEventDto.getUserId()).orElseThrow(() -> new ApplicationException("User not found"));
    if (!user.getRole().name().equals(Users.Role.ORGANIZER.name())) {
      throw new ApplicationException("Only Organizer can create an event");
    }

    Optional<Event> eventExists = eventRepository.findById(requestEventDto.getId());
    if (eventExists.isEmpty()) {
      throw new ApplicationException("Event with id " + requestEventDto.getId() + " not exists");
    }




    return null;
  }

  @Override
  public String deleteEvent(Long eventId) {
    eventRepository.deleteById(eventId);
    return "Delete event success";
  }
}
