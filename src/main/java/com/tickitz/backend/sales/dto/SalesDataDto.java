package com.tickitz.backend.sales.dto;

import lombok.Data;


import java.time.LocalDate;
import java.time.LocalDateTime;


@Data
public class SalesDataDto {
  private Long eventId;
  private String eventName;
  private Long totalSales;
  private LocalDateTime date;
}
