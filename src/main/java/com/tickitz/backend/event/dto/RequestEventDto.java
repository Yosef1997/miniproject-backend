package com.tickitz.backend.event.dto;

import com.tickitz.backend.promotion.entity.Promotion;
import com.tickitz.backend.ticket.entity.Ticket;
import lombok.Data;

import java.time.Instant;
import java.util.List;

@Data
public class RequestEventDto {
  private Long id;
  private String eventName;
  private String eventImage;
  private String category;
  private String location;
  private String venue;
  private String description;
  private String date;
  private String startTime;
  private String endTime;
  private Long userId;
  private List<Ticket> tickets;
  private List<Promotion> promotions;
}
