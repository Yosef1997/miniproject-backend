package com.tickitz.backend.ticketOrder.service;

import com.tickitz.backend.ticketOrder.dto.CreateTicketOrderRequestDto;
import com.tickitz.backend.ticketOrder.dto.TicketOrderResponseDto;
import com.tickitz.backend.ticketOrder.dto.UpdateTicketOrderRequestDto;
import com.tickitz.backend.ticketOrder.entity.TicketOrder;

import java.util.List;

public interface TicketOrderService {
  List<TicketOrderResponseDto> getAllTicketOrders(Long orderId);
  TicketOrderResponseDto getDetailTicketOrder(Long id);
  TicketOrder getDetail(Long id);
  TicketOrderResponseDto createTicketOrder(CreateTicketOrderRequestDto createTicketOrderRequestDto);
  TicketOrderResponseDto updateTicketOrder(UpdateTicketOrderRequestDto updateTicketOrderRequestDto);
  String deleteTicketOrder(Long id);
}
