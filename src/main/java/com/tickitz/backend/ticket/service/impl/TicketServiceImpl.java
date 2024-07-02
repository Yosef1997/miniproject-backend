package com.tickitz.backend.ticket.service.impl;

import com.tickitz.backend.event.service.EventService;
import com.tickitz.backend.exceptions.applicationException.ApplicationException;
import com.tickitz.backend.ticket.dto.TicketDto;
import com.tickitz.backend.ticket.entity.Ticket;
import com.tickitz.backend.ticket.repository.TicketRepository;
import com.tickitz.backend.ticket.service.TicketService;
import lombok.extern.java.Log;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Log
public class TicketServiceImpl implements TicketService {
  private final TicketRepository ticketRepository;
  private final EventService eventService;
  public TicketServiceImpl(TicketRepository ticketRepository, EventService eventService) {
    this.ticketRepository = ticketRepository;
    this.eventService = eventService;
  }

  @Override
  public List<Ticket> getAllTickets() {
    return ticketRepository.findAll();
  }

  @Override
  public Optional<Ticket> getDetailTicket(Long id) {
    return ticketRepository.findById(id);
  }

  @Override
  public Ticket createTicket(TicketDto ticketDto) {
    Ticket newTicket = new Ticket();
    newTicket.setName(ticketDto.getName());
    newTicket.setSeats(ticketDto.getSeats());
    newTicket.setPrice(ticketDto.getPrice());
    newTicket.setEventId(ticketDto.getEventId());
    return ticketRepository.save(newTicket);
  }

  @Override
  public Ticket updateTicket(TicketDto ticketDto) {
    Ticket ticket = ticketRepository.findById(ticketDto.getId()).orElseThrow(()-> new ApplicationException("Ticket not exists"));
    ticket.setName(ticketDto.getName());
    ticket.setSeats(ticketDto.getSeats());
    ticket.setPrice(ticketDto.getPrice());
    return ticketRepository.save(ticket);
  }

  @Override
  public String deleteTicket(Long id) {
    ticketRepository.deleteById(id);
    return "Delete Ticket Success";
  }
}
