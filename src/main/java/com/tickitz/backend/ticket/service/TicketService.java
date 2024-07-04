package com.tickitz.backend.ticket.service;

import com.tickitz.backend.ticket.dao.TicketDao;
import com.tickitz.backend.ticket.dto.CreateTicketRequestDto;
import com.tickitz.backend.ticket.dto.TicketResponseDto;
import com.tickitz.backend.ticket.dto.UpdateTicketRequestDto;
import com.tickitz.backend.ticket.entity.Ticket;

import java.util.List;
import java.util.Optional;

public interface TicketService {
  List<TicketDao> getAllTickets(Long eventId);
  TicketResponseDto getDetailTicket(Long id);
  TicketResponseDto createTicket(CreateTicketRequestDto createTicketRequestDto);
  TicketResponseDto updateTicket(UpdateTicketRequestDto updateTicketRequestDto);
  String deleteTicket(Long id);
}
