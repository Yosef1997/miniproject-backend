package com.tickitz.backend.order.service;

import com.tickitz.backend.order.dto.CreateOrderRequestDto;
import com.tickitz.backend.order.dto.OrderResponseDto;
import com.tickitz.backend.order.dto.UpdateOrderRequestDto;
import com.tickitz.backend.order.entity.Order;
import com.tickitz.backend.sales.dao.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;
import java.util.List;

public interface OrderService {
  Page<OrderResponseDto> getAllOrders(Pageable pageable);

  OrderResponseDto getDetailOrder(Long id);

  Order getDetail(Long id);

  OrderResponseDto createOrder(CreateOrderRequestDto createOrderRequestDto);

  OrderResponseDto updateOrder(UpdateOrderRequestDto updateOrderRequestDto);

  String deleteOrder(Long id);

  List<SalesDataDao> aggregateSalesDataHourly(Long eventId, LocalDateTime startDateTime, LocalDateTime endDateTime);

  List<SalesDataDao> aggregateSalesDataDaily(Long eventId, LocalDateTime startDateTime, LocalDateTime endDateTime);

  List<SalesDataDao> aggregateSalesDataMonthly(Long eventId, LocalDateTime startDateTime, LocalDateTime endDateTime);

  List<SalesOrganizerDao> aggregateOrganizerSalesDataHourly(Long organizerId, LocalDateTime startDateTime, LocalDateTime endDateTime);

  List<SalesOrganizerDao> aggregateOrganizerSalesDataDaily(Long organizerId, LocalDateTime startDateTime, LocalDateTime endDateTime);

  List<SalesOrganizerDao> aggregateOrganizerSalesDataMonthly(Long organizerId, LocalDateTime startDateTime, LocalDateTime endDateTime);

  List<SalesTicketDao> aggregateSalesTicketHourly(Long eventId, LocalDateTime startDateTime, LocalDateTime endDateTime);

  List<SalesTicketDao> aggregateSalesDTicketDaily(Long eventId, LocalDateTime startDateTime, LocalDateTime endDateTime);

  List<SalesTicketDao> aggregateSalesTicketMonthly(Long eventId, LocalDateTime startDateTime, LocalDateTime endDateTime);

  List<SalesTicketOrganizerDao> aggregateOrganizerSalesTicketHourly(Long organizerId, LocalDateTime startDateTime, LocalDateTime endDateTime);

  List<SalesTicketOrganizerDao> aggregateOrganizerSalesTicketDaily(Long organizerId, LocalDateTime startDateTime, LocalDateTime endDateTime);

  List<SalesTicketOrganizerDao> aggregateOrganizerSalesTicketMonthly(Long organizerId, LocalDateTime startDateTime, LocalDateTime endDateTime);

  SalesTotalSalesTotalTicketEventDao getTotalSalesTotalTicketEvent(Long eventId);

  SalesTotalSalesTotalTicketOrganizerDao getTotalSalesTotalTicketOrganizer(Long organizerId);
}
