package com.tickitz.backend.review.dto;

import com.tickitz.backend.event.service.EventService;
import com.tickitz.backend.review.entity.Review;
import com.tickitz.backend.users.service.UsersService;
import lombok.Data;

@Data
public class CreateReviewRequestDto {
  private String experience;
  private String quality;
  private String improvement;
  private Double rating;
  private Long userId;
  private Long eventId;

  public Review toEntity(UsersService usersService, EventService eventService) {
    Review newReview = new Review();
    newReview.setExperience(experience);
    newReview.setQuality(quality);
    newReview.setImprovement(improvement);
    newReview.setRating(rating);
    newReview.setUser(usersService.getDetailUserId(userId));
    newReview.setEvent(eventService.getDetail(eventId));
    return newReview;
  }
}