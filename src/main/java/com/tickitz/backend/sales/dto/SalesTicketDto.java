package com.tickitz.backend.sales.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class SalesTicketDto {
  private Long eventId;
  private String eventName;
  private Long totalTicket;
  private LocalDateTime date;
}
