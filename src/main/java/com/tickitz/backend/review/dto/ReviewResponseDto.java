package com.tickitz.backend.review.dto;

import lombok.Data;

import java.time.Instant;

@Data
public class ReviewResponseDto {
  private Long id;
  private String experience;
  private String quality;
  private String improvement;
  private Double rating;
  private Long userId;
  private Long eventId;
  private Instant createdAt;
  private Instant updatedAt;
  private Instant deletedAt;
}
