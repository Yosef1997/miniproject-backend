package com.tickitz.backend.ticket.service;

import com.tickitz.backend.ticket.dto.TicketDto;
import com.tickitz.backend.ticket.entity.Ticket;

import java.util.List;
import java.util.Optional;

public interface TicketService {
  List<Ticket> getAllTickets();
  Optional<Ticket> getDetailTicket(Long id);
  Ticket createTicket(TicketDto ticketDto);
  Ticket updateTicket(TicketDto ticketDto);
  String deleteTicket(Long id);
}
