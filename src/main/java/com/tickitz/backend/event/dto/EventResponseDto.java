package com.tickitz.backend.event.dto;

import com.tickitz.backend.promotion.dao.PromotionDao;
import com.tickitz.backend.promotion.entity.Promotion;
import com.tickitz.backend.review.dto.ReviewResponseDto;
import com.tickitz.backend.ticket.dao.TicketDao;
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
  private List<TicketDao> tickets;
  private List<PromotionDao> promotions;
}
