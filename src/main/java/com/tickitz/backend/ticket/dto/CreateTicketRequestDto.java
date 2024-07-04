package com.tickitz.backend.ticket.dto;

import com.tickitz.backend.event.entity.Event;
import com.tickitz.backend.event.repository.EventRepository;
import com.tickitz.backend.exceptions.applicationException.ApplicationException;
import com.tickitz.backend.ticket.entity.Ticket;
import lombok.Data;

@Data
public class CreateTicketRequestDto {
  private String name;
  private Integer seats;
  private Long price;
  private Long eventId;

  public Ticket toEntity(EventRepository eventRepository) {
    Event existsEvent = eventRepository.findById(eventId).orElseThrow(()-> new ApplicationException("Event not exists"));
    Ticket newTicket = new Ticket();
    newTicket.setName(name);
    newTicket.setSeats(seats);
    newTicket.setPrice(price);
    newTicket.setEvent(existsEvent);
    return newTicket;
  }
}
