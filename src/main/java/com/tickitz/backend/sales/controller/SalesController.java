package com.tickitz.backend.sales.controller;

import com.tickitz.backend.response.Response;
import com.tickitz.backend.sales.dao.SalesTotalSalesTotalTicketEventDao;
import com.tickitz.backend.sales.dao.SalesTotalSalesTotalTicketOrganizerDao;
import com.tickitz.backend.sales.dto.SalesDataDto;
import com.tickitz.backend.sales.dto.SalesOrganizerDto;
import com.tickitz.backend.sales.dto.SalesTicketDto;
import com.tickitz.backend.sales.dto.SalesTicketOrganizerDto;
import com.tickitz.backend.sales.service.SalesService;
import lombok.extern.java.Log;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/sales")
@Validated
@Log
public class SalesController {
  private final SalesService salesService;

  public SalesController(SalesService salesService) {
    this.salesService = salesService;
  }

  @GetMapping("/daily/{eventId}")
  public ResponseEntity<Response<List<SalesDataDto>>> getDailySalesData(@PathVariable Long eventId) {
    return Response.successResponse("Daily Event Sales Fetched", salesService.getDailySalesDataForEvent(eventId));

  }

  @GetMapping("/monthly/{eventId}")
  public ResponseEntity<Response<List<SalesDataDto>>> getMonthlySalesData(@PathVariable Long eventId) {
    return Response.successResponse("Monthly Event Sales Fetched", salesService.getMonthlySalesDataForEvent(eventId));

  }

  @GetMapping("/yearly/{eventId}")
  public ResponseEntity<Response<List<SalesDataDto>>> getYearlySalesData(@PathVariable Long eventId) {
    return Response.successResponse("Yearly Event Sales Fetched", salesService.getYearlySalesDataForEvent(eventId));

  }

  @GetMapping("/organizer/daily/{organizerId}")
  public ResponseEntity<Response<List<SalesOrganizerDto>>> getDailySalesOrganizerData(@PathVariable Long organizerId) {
    return Response.successResponse("Daily Organizer Sales Fetched", salesService.getDailySalesDataForOrganizer(organizerId));

  }

  @GetMapping("/organizer/monthly/{organizerId}")
  public ResponseEntity<Response<List<SalesOrganizerDto>>> getMonthlySalesOrganizerData(@PathVariable Long organizerId) {
    return Response.successResponse("Monthly Organizer Sales Fetched", salesService.getMonthlySalesDataForOrganizer(organizerId));

  }

  @GetMapping("/organizer/yearly/{organizerId}")
  public ResponseEntity<Response<List<SalesOrganizerDto>>> getYearlySalesOrganizerData(@PathVariable Long organizerId) {
    return Response.successResponse("Yearly Organizer Sales Fetched", salesService.getYearlySalesDataForOrganizer(organizerId));
  }

  @GetMapping("/ticket/daily/{eventId}")
  public ResponseEntity<Response<List<SalesTicketDto>>> getDailySalesTicket(@PathVariable Long eventId) {
    return Response.successResponse("Daily Ticket Sales Fetched", salesService.getDailySalesTicketForEvent(eventId));
  }

  @GetMapping("/ticket/monthly/{eventId}")
  public ResponseEntity<Response<List<SalesTicketDto>>> getMonthlySalesTicket(@PathVariable Long eventId) {
    return Response.successResponse("Monthly Ticket Sales Fetched", salesService.getMonthlySalesTicketForEvent(eventId));
  }

  @GetMapping("/ticket/yearly/{eventId}")
  public ResponseEntity<Response<List<SalesTicketDto>>> getYearlySalesTicket(@PathVariable Long eventId) {
    return Response.successResponse("Yearly Ticket Sales Fetched", salesService.getYearlySalesTicketForEvent(eventId));
  }

  @GetMapping("/organizer/ticket/daily/{organizerId}")
  public ResponseEntity<Response<List<SalesTicketOrganizerDto>>> getDailySalesOrganizerTicket(@PathVariable Long organizerId) {
    return Response.successResponse("Daily Organizer Ticket Sales Fetched", salesService.getDailySalesTicketForOrganizer(organizerId));

  }

  @GetMapping("/organizer/ticket/monthly/{organizerId}")
  public ResponseEntity<Response<List<SalesTicketOrganizerDto>>> getMonthlySalesOrganizerTicket(@PathVariable Long organizerId) {
    return Response.successResponse("Monthly Organizer Ticket Sales Fetched", salesService.getMonthlySalesTicketForOrganizer(organizerId));

  }

  @GetMapping("/organizer/ticket/yearly/{organizerId}")
  public ResponseEntity<Response<List<SalesTicketOrganizerDto>>> getYearlySalesOrganizerTicket(@PathVariable Long organizerId) {
    return Response.successResponse("Yearly Organizer Ticket Sales Fetched", salesService.getYearlySalesTicketForOrganizer(organizerId));
  }

  @GetMapping("/event/total/{eventId}")
  public ResponseEntity<Response<SalesTotalSalesTotalTicketEventDao>> getTotalSalesTotalTicketEvent(@PathVariable Long eventId) {
    return Response.successResponse("Total sales and total ticket sold Event", salesService.getTotalSalesTotalTicketEvent(eventId));
  }

  @GetMapping("/organizer/total/{organizerId}")
  public ResponseEntity<Response<SalesTotalSalesTotalTicketOrganizerDao>> getTotalSalesTotalTicketOrganizer(@PathVariable Long organizerId) {
    return Response.successResponse("Total sales and total ticket sold Organizer", salesService.getTotalSalesTotalTicketOrganizer(organizerId));
  }
}
