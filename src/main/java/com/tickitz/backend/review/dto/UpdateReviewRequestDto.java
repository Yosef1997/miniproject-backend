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

  public ReviewResponseDto mapToReviewResponseDto(Review updated) {
    ReviewResponseDto response = new ReviewResponseDto();
    response.setId(updated.getId());
    response.setExperience(updated.getExperience());
    response.setQuality(updated.getQuality());
    response.setImprovement(updated.getImprovement());
    response.setRating(updated.getRating());
    response.setUserId(updated.getUser().getId());
    response.setEventId(updated.getEvent().getId());
    response.setCreatedAt(updated.getCreatedAt());
    response.setUpdatedAt(updated.getUpdatedAt());
    response.setDeletedAt(updated.getDeletedAt());
    return response;
  }
}
