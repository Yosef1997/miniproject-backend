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
import com.tickitz.backend.promotion.service.PromotionService;
import com.tickitz.backend.review.service.ReviewService;
import com.tickitz.backend.ticket.dto.CreateTicketRequestDto;
import com.tickitz.backend.ticket.service.TicketService;
import com.tickitz.backend.users.entity.Users;
import com.tickitz.backend.users.service.UsersService;
import lombok.extern.java.Log;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;


@Service
@Log
public class EventServiceImpl implements EventService {
  private final EventRepository eventRepository;
  private final UsersService usersService;
  private final ReviewService reviewService;
  private final TicketService ticketService;
  private final PromotionService promotionService;

  public EventServiceImpl(EventRepository eventRepository,  UsersService usersService, @Lazy ReviewService reviewService,
                          @Lazy TicketService ticketService, @Lazy PromotionService promotionService
  ) {
    this.eventRepository = eventRepository;
    this.usersService = usersService;
    this.reviewService = reviewService;
    this.ticketService = ticketService;
    this.promotionService = promotionService;
  }

  @Override
  public Page<EventResponseDto> getAllEvents(Pageable pageable, String eventName, String location, String category, Long userId, String upcoming) {
    Specification<Event> specification = Specification.where(EventSpecifications.byEventName(eventName))
            .and(EventSpecifications.byLocation(location))
            .and(EventSpecifications.byCategory(category))
            .and(EventSpecifications.byUserId(userId))
            .and(EventSpecifications.byUpcoming(upcoming));
    return eventRepository.findAll(specification, pageable).map(this::mapToEventAll);

  }

  @Override
  public EventResponseDto getDetailEvent(Long id) {
    Event detail = eventRepository.findById(id).orElseThrow(() -> new ApplicationException("Event not exists"));
    return mapToEventResponseDto(detail);
  }

  @Override
  public Event getDetail(Long id) {
    return eventRepository.findById(id).orElseThrow(() -> new ApplicationException("Event not exists"));
  }

  @Override
  public EventResponseDto createEvent(CreateEventRequestDto createEventRequestDto) {
    Users user = usersService.getDetailUserId(createEventRequestDto.getUserId());
    if (!user.getRole().name().equals(Users.Role.ORGANIZER.name())) {
      throw new ApplicationException("Only Organizer can create an event");
    }
    Event savedEvent = eventRepository.save(createEventRequestDto.toEntity(user));
    for (CreateTicketRequestDto ticketType : createEventRequestDto.getTickets()) {
      ticketType.setEventId(savedEvent.getId());
      ticketService.createTicket(ticketType);
    }
    for (CreatePromoRequestDto promotion : createEventRequestDto.getPromotions()) {
      promotion.setEventId(savedEvent.getId());
      promotionService.createPromotion(promotion);
    }
    return mapToEventResponseDto(savedEvent);
  }

  @Override
  public EventResponseDto updateEvent(UpdateEventRequestDto updateEventRequestDto) {
    Users user = usersService.getDetailUserId(updateEventRequestDto.getUserId());
    if (!user.getRole().name().equals(Users.Role.ORGANIZER.name())) {
      throw new ApplicationException("Only Organizer can create an event");
    }
    Event existsEvent = eventRepository.findById(updateEventRequestDto.getId()).orElseThrow(() -> new ApplicationException("Event with id " + updateEventRequestDto.getId() + " not exists"));
    Event updated = eventRepository.save(updateEventRequestDto.toEntity(existsEvent));
    return mapToEventResponseDto(updated);
  }

  @Override
  public String deleteEvent(Long eventId) {
    eventRepository.findById(eventId).orElseThrow(() -> new ApplicationException("Event not exists"));
    eventRepository.deleteById(eventId);
    return "Delete event success";
  }

  public EventResponseDto mapToEventResponseDto(Event event) {
    EventResponseDto response = new EventResponseDto();
    response.setId(event.getId());
    response.setEventName(event.getEventName());
    response.setEventImage(event.getEventImage());
    response.setCategory(event.getCategory());
    response.setLocation(event.getLocation());
    response.setVenue(event.getVenue());
    response.setDescription(event.getDescription());
    response.setDate(event.getDate());
    response.setStartTime(event.getStartTime());
    response.setEndTime(event.getEndTime());
    response.setUserId(event.getUser().getId());
    response.setReviews(reviewService.getAllReview(event.getId()));
    response.setTickets(ticketService.getAllTickets(event.getId()));
    response.setPromotions(promotionService.getAllPromotions(event.getId()));
    return response;
  }

  public EventResponseDto mapToEventAll(Event event) {
    EventResponseDto response = new EventResponseDto();
    response.setId(event.getId());
    response.setEventName(event.getEventName());
    response.setEventImage(event.getEventImage());
    response.setCategory(event.getCategory());
    response.setLocation(event.getLocation());
    response.setVenue(event.getVenue());
    response.setDescription(event.getDescription());
    response.setDate(event.getDate());
    response.setStartTime(event.getStartTime());
    response.setEndTime(event.getEndTime());
    response.setUserId(event.getUser().getId());
    response.setTickets(ticketService.getAllTickets(event.getId()));
    return response;
  }
}
