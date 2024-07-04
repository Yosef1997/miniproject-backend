package com.tickitz.backend.ticket.controller;

import com.tickitz.backend.response.Response;
import com.tickitz.backend.ticket.dao.TicketDao;
import com.tickitz.backend.ticket.dto.CreateTicketRequestDto;
import com.tickitz.backend.ticket.dto.TicketResponseDto;
import com.tickitz.backend.ticket.dto.UpdateTicketRequestDto;
import com.tickitz.backend.ticket.service.TicketService;
import lombok.extern.java.Log;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/ticket")
@Validated
@Log
public class TicketController {
  private final TicketService ticketService;

  public TicketController(TicketService ticketService) {
    this.ticketService = ticketService;
  }

  @GetMapping
  public ResponseEntity<Response<List<TicketDao>>> getAllTicket(@RequestParam(name = "eventId", required = false) Long eventId){
    return Response.successResponse("All Tickets Fetched", ticketService.getAllTickets(eventId));
  }

  @GetMapping("/{id}")
  public ResponseEntity<Response<TicketResponseDto>> getDetailTicket(@PathVariable Long id) {
    return Response.successResponse("Get Detail Event Success", ticketService.getDetailTicket(id));
  }

  @PostMapping
  public ResponseEntity<Response<TicketResponseDto>> createTicket(@RequestBody CreateTicketRequestDto createTicketRequestDto) {
    return Response.successResponse("Create Ticket Success", ticketService.createTicket(createTicketRequestDto));
  }

  @PutMapping
  public ResponseEntity<Response<TicketResponseDto>> updateTicket(@RequestBody UpdateTicketRequestDto updateTicketRequestDto) {
    return Response.successResponse("Update Ticket Success", ticketService.updateTicket(updateTicketRequestDto));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Response<Object>> deleteTicket(@PathVariable Long id) {
    return Response.successResponse(ticketService.deleteTicket(id));
  }
}
