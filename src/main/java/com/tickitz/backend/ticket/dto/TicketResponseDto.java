package com.tickitz.backend.ticket.dto;

import lombok.Data;

@Data
public class TicketResponseDto {
  private Long id;
  private String name;
  private Integer seats;
  private Long price;
  private Long eventId;
}
