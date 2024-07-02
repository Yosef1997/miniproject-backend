package com.tickitz.backend.ticket.controller;

import com.tickitz.backend.response.Response;
import com.tickitz.backend.ticket.dto.TicketDto;
import com.tickitz.backend.ticket.entity.Ticket;
import com.tickitz.backend.ticket.service.TicketService;
import lombok.extern.java.Log;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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
  public ResponseEntity<Response<List<Ticket>>> getAllTicket(){
    return Response.successResponse("All Tickets Fetched", ticketService.getAllTickets());
  }

  @GetMapping("/{id}")
  public ResponseEntity<Response<Optional<Ticket>>> getDetailTicket(@PathVariable Long id) {
    return Response.successResponse("Get Detail Event Success", ticketService.getDetailTicket(id));
  }

  @PostMapping
  public ResponseEntity<Response<Ticket>> createTicket(@RequestBody TicketDto ticketDto) {
    return Response.successResponse("Create Ticket Success", ticketService.createTicket(ticketDto));
  }

  @PutMapping
  public ResponseEntity<Response<Ticket>> updateTicket(@RequestBody TicketDto ticketDto) {
    return Response.successResponse("Update Ticket Success", ticketService.updateTicket(ticketDto));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Response<Object>> deleteTicket(@PathVariable Long id) {
    return Response.successResponse(ticketService.deleteTicket(id));
  }
}
