package com.tickitz.backend.ticket.service;

import com.tickitz.backend.ticket.dto.CreateTicketRequestDto;
import com.tickitz.backend.ticket.dto.TicketResponseDto;
import com.tickitz.backend.ticket.dto.UpdateTicketRequestDto;
import com.tickitz.backend.ticket.entity.Ticket;

import java.util.List;

public interface TicketService {
  List<TicketResponseDto> getAllTickets(Long eventId);
  TicketResponseDto getDetailTicket(Long id);
  Ticket getDetail(Long id);
  TicketResponseDto createTicket(CreateTicketRequestDto createTicketRequestDto);
  TicketResponseDto updateTicket(UpdateTicketRequestDto updateTicketRequestDto);
  String deleteTicket(Long id);
}
