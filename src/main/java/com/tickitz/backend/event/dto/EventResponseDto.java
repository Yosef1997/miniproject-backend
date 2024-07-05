package com.tickitz.backend.event.dto;

import com.tickitz.backend.promotion.dto.PromoResponseDto;
import com.tickitz.backend.review.dto.ReviewResponseDto;
import com.tickitz.backend.ticket.dto.TicketResponseDto;
import lombok.Data;

import java.time.Instant;
import java.util.List;

@Data
public class EventResponseDto {
  private Long id;
  private String eventName;
  private String eventImage;
  private String category;
  private String location;
  private String venue;
  private String description;
  private Instant date;
  private Instant startTime;
  private Instant endTime;
  private Long userId;
  private List<ReviewResponseDto> reviews;
  private List<TicketResponseDto> tickets;
  private List<PromoResponseDto> promotions;
}
