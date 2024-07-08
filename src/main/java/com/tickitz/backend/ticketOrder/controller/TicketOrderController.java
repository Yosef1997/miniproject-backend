package com.tickitz.backend.ticketOrder.controller;

import com.tickitz.backend.response.Response;
import com.tickitz.backend.ticketOrder.dto.CreateTicketOrderRequestDto;
import com.tickitz.backend.ticketOrder.dto.TicketOrderResponseDto;
import com.tickitz.backend.ticketOrder.dto.UpdateTicketOrderRequestDto;
import com.tickitz.backend.ticketOrder.service.TicketOrderService;
import lombok.extern.java.Log;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/api/v1/ticket-order")
@Validated
@Log
public class TicketOrderController {
  private final TicketOrderService ticketOrderService;

  public TicketOrderController(TicketOrderService ticketOrderService) {
    this.ticketOrderService = ticketOrderService;
  }

  @GetMapping
  public ResponseEntity<Response<List<TicketOrderResponseDto>>> getAllTicketOrders(@RequestParam(name = "orderId", required = false) Long orderId) {
    return Response.successResponse("All Ticket Orders Fetched", ticketOrderService.getAllTicketOrders(orderId));
  }

  @GetMapping("/{id}")
  public ResponseEntity<Response<TicketOrderResponseDto>> getDetailTicketOrder(@PathVariable Long id) {
    return Response.successResponse("Detail Ticket Order with id: " + id, ticketOrderService.getDetailTicketOrder(id));
  }

  @PostMapping
  public ResponseEntity<Response<TicketOrderResponseDto>> createTicketOrder(@RequestBody CreateTicketOrderRequestDto createTicketOrderRequestDto) {
    return Response.successResponse("Create Ticket Order Success", ticketOrderService.createTicketOrder(createTicketOrderRequestDto));
  }

  @PutMapping
  public ResponseEntity<Response<TicketOrderResponseDto>> updateTicketOrder(@RequestBody UpdateTicketOrderRequestDto updateTicketOrderRequestDto) {
    return Response.successResponse("Update Ticket Order Success", ticketOrderService.updateTicketOrder(updateTicketOrderRequestDto));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Response<Object>> deleteTicketOrder(@PathVariable Long id) {
    return Response.successResponse(ticketOrderService.deleteTicketOrder(id));
  }
}
