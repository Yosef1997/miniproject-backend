package com.tickitz.backend.ticket.service.impl;

import com.tickitz.backend.event.repository.EventRepository;
import com.tickitz.backend.event.service.EventService;
import com.tickitz.backend.exceptions.applicationException.ApplicationException;
import com.tickitz.backend.ticket.dto.CreateTicketRequestDto;
import com.tickitz.backend.ticket.dto.TicketResponseDto;
import com.tickitz.backend.ticket.dto.UpdateTicketRequestDto;
import com.tickitz.backend.ticket.entity.Ticket;
import com.tickitz.backend.ticket.repository.TicketRepository;
import com.tickitz.backend.ticket.service.TicketService;
import lombok.extern.java.Log;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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
  public List<TicketResponseDto> getAllTickets(Long eventId) {
    if (eventId != null) {
      List<Ticket> result = ticketRepository.findAllByEventId(eventId);
      return result.stream().map(this::mapToTicketResponseDto).collect(Collectors.toList());
    }
    List<Ticket> result = ticketRepository.findAll();
    return result.stream().map(this::mapToTicketResponseDto).collect(Collectors.toList());
  }

  @Override
  public TicketResponseDto getDetailTicket(Long id) {
    Ticket detail = ticketRepository.findById(id).orElseThrow(() -> new ApplicationException("Ticket not exists"));
    return mapToTicketResponseDto(detail);
  }


  @Override
  public TicketResponseDto createTicket(CreateTicketRequestDto createTicketRequestDto) {
    Ticket saved = ticketRepository.save(createTicketRequestDto.toEntity(eventService));
    return mapToTicketResponseDto(saved);
  }

  @Override
  public TicketResponseDto updateTicket(UpdateTicketRequestDto updateTicketRequestDto) {
    Ticket ticket = ticketRepository.findById(updateTicketRequestDto.getId()).orElseThrow(() -> new ApplicationException("Ticket not exists"));
    Ticket updated = ticketRepository.save(updateTicketRequestDto.toEntity(ticket));
    return mapToTicketResponseDto(updated);
  }

  @Override
  public String deleteTicket(Long id) {
    ticketRepository.deleteById(id);
    return "Delete Ticket Success";
  }

  public TicketResponseDto mapToTicketResponseDto(Ticket ticket) {
    TicketResponseDto response = new TicketResponseDto();
    response.setId(ticket.getId());
    response.setName(ticket.getName());
    response.setSeats(ticket.getSeats());
    response.setPrice(ticket.getPrice());
    response.setEventId(ticket.getEvent().getId());
    return response;
  }
}
