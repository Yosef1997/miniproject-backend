package com.tickitz.backend.review.dto;

import com.tickitz.backend.review.entity.Review;
import lombok.Data;

@Data
public class UpdateReviewRequestDto {
  private Long id;
  private String experience;
  private String quality;
  private String improvement;
  private Double rating;
}
