package com.tickitz.backend.sales.service.impl;

import com.tickitz.backend.order.repository.OrderRepository;
import com.tickitz.backend.order.service.OrderService;
import com.tickitz.backend.sales.dao.*;
import com.tickitz.backend.sales.dto.*;
import com.tickitz.backend.sales.service.SalesService;
import lombok.extern.java.Log;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Log
public class SalesServiceImpl implements SalesService {
  private final OrderService orderService;

  public SalesServiceImpl(OrderService orderService) {
    this.orderService = orderService;
  }

  @Override
  public List<SalesDataDto> getDailySalesDataForEvent(Long eventId) {
    LocalDateTime startDateTime = LocalDateTime.now().minusDays(1);
    LocalDateTime endDateTime = startDateTime.plusDays(1).minusNanos(1);
    return orderService.aggregateSalesDataHourly(eventId, startDateTime, endDateTime)
            .stream()
            .map(this::mapToSalesDataDto)
            .collect(Collectors.toList());
  }

  @Override
  public List<SalesDataDto> getMonthlySalesDataForEvent(Long eventId) {
    LocalDateTime now = LocalDateTime.now();
    LocalDateTime startDate = now.withDayOfMonth(1).minusMonths(1);
    LocalDateTime endDate = now.withDayOfMonth(30);
    return orderService.aggregateSalesDataDaily(eventId, startDate, endDate)
            .stream()
            .map(this::mapToSalesDataDto)
            .collect(Collectors.toList());
  }

  @Override
  public List<SalesDataDto> getYearlySalesDataForEvent(Long eventId) {
    LocalDateTime now = LocalDateTime.now();
    LocalDateTime startDate = now.withDayOfYear(1);
    LocalDateTime endDate = now.withMonth(12).withDayOfMonth(31);
    return orderService.aggregateSalesDataMonthly(eventId, startDate, endDate)
            .stream()
            .map(this::mapToSalesDataDto)
            .collect(Collectors.toList());
  }

  @Override
  public List<SalesOrganizerDto> getDailySalesDataForOrganizer(Long organizerId) {
    LocalDateTime startDateTime = LocalDateTime.now().minusDays(1);
    LocalDateTime endDateTime = startDateTime.plusDays(1).minusNanos(1);
    return orderService.aggregateOrganizerSalesDataHourly(organizerId, startDateTime, endDateTime)
            .stream()
            .map(this::mapToSalesOrganizerDto)
            .collect(Collectors.toList());
  }

  @Override
  public List<SalesOrganizerDto> getMonthlySalesDataForOrganizer(Long organizerId) {
    LocalDateTime now = LocalDateTime.now();
    LocalDateTime startDate = now.withDayOfMonth(1).minusMonths(1);
    LocalDateTime endDate = now.withDayOfMonth(30);
    return orderService.aggregateOrganizerSalesDataDaily(organizerId, startDate, endDate)
            .stream()
            .map(this::mapToSalesOrganizerDto)
            .collect(Collectors.toList());
  }

  @Override
  public List<SalesOrganizerDto> getYearlySalesDataForOrganizer(Long organizerId) {
    LocalDateTime now = LocalDateTime.now();
    LocalDateTime startDate = now.withDayOfYear(1);
    LocalDateTime endDate = now.withMonth(12).withDayOfMonth(31);
    return orderService.aggregateOrganizerSalesDataMonthly(organizerId, startDate, endDate)
            .stream()
            .map(this::mapToSalesOrganizerDto)
            .collect(Collectors.toList());
  }

  @Override
  public List<SalesTicketDto> getDailySalesTicketForEvent(Long eventId) {
    LocalDateTime startDateTime = LocalDateTime.now().minusDays(1);
    LocalDateTime endDateTime = startDateTime.plusDays(1).minusNanos(1);
    return orderService.aggregateSalesTicketHourly(eventId, startDateTime, endDateTime)
            .stream()
            .map(this::mapToSalesTicketDto)
            .collect(Collectors.toList());
  }

  @Override
  public List<SalesTicketDto> getMonthlySalesTicketForEvent(Long eventId) {
    LocalDateTime now = LocalDateTime.now();
    LocalDateTime startDate = now.withDayOfMonth(1).minusMonths(1);
    LocalDateTime endDate = now.withDayOfMonth(30);
    return orderService.aggregateSalesDTicketDaily(eventId, startDate, endDate)
            .stream()
            .map(this::mapToSalesTicketDto)
            .collect(Collectors.toList());
  }

  @Override
  public List<SalesTicketDto> getYearlySalesTicketForEvent(Long eventId) {
    LocalDateTime now = LocalDateTime.now();
    LocalDateTime startDate = now.withDayOfYear(1);
    LocalDateTime endDate = now.withMonth(12).withDayOfMonth(31);
    return orderService.aggregateSalesTicketMonthly(eventId, startDate, endDate)
            .stream()
            .map(this::mapToSalesTicketDto)
            .collect(Collectors.toList());
  }

  @Override
  public List<SalesTicketOrganizerDto> getDailySalesTicketForOrganizer(Long organizerId) {
    LocalDateTime startDateTime = LocalDateTime.now().minusDays(1);
    LocalDateTime endDateTime = startDateTime.plusDays(1).minusNanos(1);
    return orderService.aggregateOrganizerSalesTicketHourly(organizerId, startDateTime, endDateTime)
            .stream()
            .map(this::mapToSalesTicketOrganizerDto)
            .collect(Collectors.toList());
  }

  @Override
  public List<SalesTicketOrganizerDto> getMonthlySalesTicketForOrganizer(Long organizerId) {
    LocalDateTime now = LocalDateTime.now();
    LocalDateTime startDate = now.withDayOfMonth(1).minusMonths(1);
    LocalDateTime endDate = now.withDayOfMonth(30);
    return orderService.aggregateOrganizerSalesTicketDaily(organizerId, startDate, endDate)
            .stream()
            .map(this::mapToSalesTicketOrganizerDto)
            .collect(Collectors.toList());
  }

  @Override
  public List<SalesTicketOrganizerDto> getYearlySalesTicketForOrganizer(Long organizerId) {
    LocalDateTime now = LocalDateTime.now();
    LocalDateTime startDate = now.withDayOfYear(1);
    LocalDateTime endDate = now.withMonth(12).withDayOfMonth(31);
    return orderService.aggregateOrganizerSalesTicketMonthly(organizerId, startDate, endDate)
            .stream()
            .map(this::mapToSalesTicketOrganizerDto)
            .collect(Collectors.toList());
  }

  @Override
  public SalesTotalSalesTotalTicketEventDao getTotalSalesTotalTicketEvent(Long eventId) {
    return orderService.getTotalSalesTotalTicketEvent(eventId);
  }

  @Override
  public SalesTotalSalesTotalTicketOrganizerDao getTotalSalesTotalTicketOrganizer(Long organizerId) {
    return orderService.getTotalSalesTotalTicketOrganizer(organizerId);
  }


  public SalesDataDto mapToSalesDataDto(SalesDataDao sales) {
    SalesDataDto response = new SalesDataDto();
    response.setEventId(sales.getEventId());
    response.setEventName(sales.getEventName());
    response.setTotalSales(sales.getTotalSales());
    response.setDate(sales.getDate().atZone(ZoneId.systemDefault()).toLocalDateTime());
    return response;
  }

  public SalesOrganizerDto mapToSalesOrganizerDto(SalesOrganizerDao sales) {
    SalesOrganizerDto response = new SalesOrganizerDto();
    response.setOrganizerId(sales.getOrganizerId());
    response.setTotalSales(sales.getTotalSales());
    response.setDate(sales.getDate().atZone(ZoneId.systemDefault()).toLocalDateTime());
    return response;
  }

  public SalesTicketDto mapToSalesTicketDto(SalesTicketDao sales) {
    SalesTicketDto response = new SalesTicketDto();
    response.setEventId(sales.getEventId());
    response.setEventName(sales.getEventName());
    response.setTotalTicket(sales.getTotalTicket());
    response.setDate(sales.getDate().atZone(ZoneId.systemDefault()).toLocalDateTime());
    return response;
  }

  public SalesTicketOrganizerDto mapToSalesTicketOrganizerDto(SalesTicketOrganizerDao sales) {
    SalesTicketOrganizerDto response = new SalesTicketOrganizerDto();
    response.setOrganizerId(sales.getOrganizerId());
    response.setTotalTicket(sales.getTotalTicket());
    response.setDate(sales.getDate().atZone(ZoneId.systemDefault()).toLocalDateTime());
    return response;
  }
}
