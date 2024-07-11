package com.tickitz.backend.sales.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class SalesTicketOrganizerDto {
  private Long organizerId;
  private Long totalTicket;
  private LocalDateTime date;
}
