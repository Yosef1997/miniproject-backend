package com.tickitz.backend.sales.service;

import com.tickitz.backend.sales.dao.SalesTotalSalesTotalTicketEventDao;
import com.tickitz.backend.sales.dao.SalesTotalSalesTotalTicketOrganizerDao;
import com.tickitz.backend.sales.dto.*;

import java.time.Instant;
import java.util.List;

public interface SalesService {
  List<SalesDataDto> getDailySalesDataForEvent(Long eventId);

  List<SalesDataDto> getMonthlySalesDataForEvent(Long eventId);

  List<SalesDataDto> getYearlySalesDataForEvent(Long eventId);

  List<SalesOrganizerDto> getDailySalesDataForOrganizer(Long organizerId);

  List<SalesOrganizerDto> getMonthlySalesDataForOrganizer(Long organizerId);

  List<SalesOrganizerDto> getYearlySalesDataForOrganizer(Long organizerId);

  List<SalesTicketDto> getDailySalesTicketForEvent(Long eventId);

  List<SalesTicketDto> getMonthlySalesTicketForEvent(Long eventId);

  List<SalesTicketDto> getYearlySalesTicketForEvent(Long eventId);

  List<SalesTicketOrganizerDto> getDailySalesTicketForOrganizer(Long organizerId);

  List<SalesTicketOrganizerDto> getMonthlySalesTicketForOrganizer(Long organizerId);

  List<SalesTicketOrganizerDto> getYearlySalesTicketForOrganizer(Long organizerId);

  SalesTotalSalesTotalTicketEventDao getTotalSalesTotalTicketEvent(Long eventId);

  SalesTotalSalesTotalTicketOrganizerDao getTotalSalesTotalTicketOrganizer(Long organizerId);
}
