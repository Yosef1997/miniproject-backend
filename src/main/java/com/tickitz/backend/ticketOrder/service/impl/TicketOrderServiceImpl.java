package com.tickitz.backend.ticketOrder.service.impl;

import com.tickitz.backend.exceptions.applicationException.ApplicationException;
import com.tickitz.backend.order.service.OrderService;
import com.tickitz.backend.ticket.service.TicketService;
import com.tickitz.backend.ticketOrder.dto.CreateTicketOrderRequestDto;
import com.tickitz.backend.ticketOrder.dto.TicketOrderResponseDto;
import com.tickitz.backend.ticketOrder.dto.UpdateTicketOrderRequestDto;
import com.tickitz.backend.ticketOrder.entity.TicketOrder;
import com.tickitz.backend.ticketOrder.repository.TicketOrderRepository;
import com.tickitz.backend.ticketOrder.service.TicketOrderService;
import lombok.extern.java.Log;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Log
public class TicketOrderServiceImpl implements TicketOrderService {
  private final TicketOrderRepository ticketOrderRepository;
  private final OrderService orderService;
  private final TicketService ticketService;
  public TicketOrderServiceImpl(TicketOrderRepository ticketOrderRepository,
                                OrderService orderService,
                                TicketService ticketService) {
    this.ticketOrderRepository = ticketOrderRepository;
    this.orderService = orderService;
    this.ticketService = ticketService;
  }

  @Override
  public List<TicketOrderResponseDto> getAllTicketOrders(Long orderId) {
    if (orderId == null) {
      List<TicketOrder> result = ticketOrderRepository.findAll();
      return result.stream().map(this::mapToTicketOrderRepository).collect(Collectors.toList());
    }
    List<TicketOrder> result = ticketOrderRepository.findAllByOrderId(orderId);
    return result.stream().map(this::mapToTicketOrderRepository).collect(Collectors.toList());
  }

  @Override
  public TicketOrderResponseDto getDetailTicketOrder(Long id) {
    TicketOrder detail = getDetail(id);
    return mapToTicketOrderRepository(detail);
  }

  @Override
  public TicketOrder getDetail(Long id) {
    return ticketOrderRepository.findById(id).orElseThrow(()-> new ApplicationException("Ticket order with id: " +id+ " not exists"));
  }

  @Override
  public TicketOrderResponseDto createTicketOrder(CreateTicketOrderRequestDto createTicketOrderRequestDto) {
    TicketOrder newTicketOrder = createTicketOrderRequestDto.toEntity();
    newTicketOrder.setOrder(orderService.getDetail(createTicketOrderRequestDto.getOrderId()));
    newTicketOrder.setTicket(ticketService.getDetail(createTicketOrderRequestDto.getTicketId()));
    TicketOrder result = ticketOrderRepository.save(newTicketOrder);
    return mapToTicketOrderRepository(result);
  }

  @Override
  public TicketOrderResponseDto updateTicketOrder(UpdateTicketOrderRequestDto updateTicketOrderRequestDto) {
    TicketOrder result = getDetail(updateTicketOrderRequestDto.getId());
    TicketOrder updated = ticketOrderRepository.save(updateTicketOrderRequestDto.toEntity(result));
    return mapToTicketOrderRepository(updated);
  }

  @Override
  public String deleteTicketOrder(Long id) {
    getDetail(id);
    ticketOrderRepository.deleteById(id);
    return "Delete Ticket Order Success";
  }

  public TicketOrderResponseDto mapToTicketOrderRepository(TicketOrder ticketOrder){
    TicketOrderResponseDto response = new TicketOrderResponseDto();
    response.setId(ticketOrder.getId());
    response.setOrderId(ticketOrder.getOrder().getId());
    response.setTicket(ticketService.getDetailTicket(ticketOrder.getTicket().getId()));
    response.setQuantity(ticketOrder.getQuantity());
    return response;
  }
}
