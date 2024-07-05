package com.tickitz.backend.review.dto;

import com.tickitz.backend.users.entity.Users;
import lombok.Data;

import java.time.Instant;

@Data
public class ReviewResponseDto {
  private Long id;
  private String experience;
  private String quality;
  private String improvement;
  private Double rating;
  private Users user;
  private Long eventId;
  private Instant createdAt;
  private Instant updatedAt;
  private Instant deletedAt;
}
