package com.tickitz.backend.ticket.dto;

import lombok.Data;

@Data
public class UpdateTicketRequestDto {
  private Long id;
  private String name;
  private Integer seats;
  private Long price;
}
