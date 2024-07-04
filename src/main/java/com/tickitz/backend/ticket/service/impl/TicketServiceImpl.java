package com.tickitz.backend.ticket.service.impl;

import com.tickitz.backend.event.entity.Event;
import com.tickitz.backend.event.repository.EventRepository;
import com.tickitz.backend.event.service.EventService;
import com.tickitz.backend.exceptions.applicationException.ApplicationException;
import com.tickitz.backend.ticket.dao.TicketDao;
import com.tickitz.backend.ticket.dto.CreateTicketRequestDto;
import com.tickitz.backend.ticket.dto.TicketResponseDto;
import com.tickitz.backend.ticket.dto.UpdateTicketRequestDto;
import com.tickitz.backend.ticket.entity.Ticket;
import com.tickitz.backend.ticket.repository.TicketRepository;
import com.tickitz.backend.ticket.service.TicketService;
import com.tickitz.backend.users.entity.Users;
import lombok.extern.java.Log;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Log
public class TicketServiceImpl implements TicketService {
  private final TicketRepository ticketRepository;
  private final EventRepository eventRepository;

  public TicketServiceImpl(TicketRepository ticketRepository, EventRepository eventRepository) {
    this.ticketRepository = ticketRepository;
    this.eventRepository = eventRepository;
  }

  @Override
  public List<TicketDao> getAllTickets(Long eventId) {
    if (eventId != null) {
      return ticketRepository.findAllTicketByEventId(eventId);
    }
    return ticketRepository.findAllTicket();
  }

  @Override
  public TicketResponseDto getDetailTicket(Long id) {
    Ticket detail = ticketRepository.findById(id).orElseThrow(() -> new ApplicationException("Ticket not exists"));
    TicketResponseDto response = new TicketResponseDto();
    response.setId(detail.getId());
    response.setName(detail.getName());
    response.setSeats(detail.getSeats());
    response.setPrice(detail.getPrice());
    response.setEventId(detail.getEvent().getId());
    return response;
  }


  @Override
  public TicketResponseDto createTicket(CreateTicketRequestDto createTicketRequestDto) {
    Ticket saved = ticketRepository.save(createTicketRequestDto.toEntity(eventRepository));
    TicketResponseDto response = new TicketResponseDto();
    response.setId(saved.getId());
    response.setName(saved.getName());
    response.setSeats(saved.getSeats());
    response.setPrice(saved.getPrice());
    response.setEventId(saved.getEvent().getId());
    return response;
  }

  @Override
  public TicketResponseDto updateTicket(UpdateTicketRequestDto updateTicketRequestDto) {
    Ticket ticket = ticketRepository.findById(updateTicketRequestDto.getId()).orElseThrow(() -> new ApplicationException("Ticket not exists"));
    ticket.setName(updateTicketRequestDto.getName());
    ticket.setSeats(updateTicketRequestDto.getSeats());
    ticket.setPrice(updateTicketRequestDto.getPrice());
    Ticket updated = ticketRepository.save(ticket);

    TicketResponseDto response = new TicketResponseDto();
    response.setId(updated.getId());
    response.setName(updated.getName());
    response.setSeats(updated.getSeats());
    response.setPrice(updated.getPrice());
    response.setEventId(updated.getEvent().getId());
    return response;
  }

  @Override
  public String deleteTicket(Long id) {
    ticketRepository.deleteById(id);
    return "Delete Ticket Success";
  }
}
