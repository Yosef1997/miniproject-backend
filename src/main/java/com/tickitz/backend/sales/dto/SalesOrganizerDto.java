package com.tickitz.backend.sales.dto;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class SalesOrganizerDto {
  private Long organizerId;
  private Long totalSales;
  private LocalDateTime date;
}
