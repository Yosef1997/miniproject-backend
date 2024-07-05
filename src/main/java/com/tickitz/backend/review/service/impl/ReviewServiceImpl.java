package com.tickitz.backend.review.service.impl;

import com.tickitz.backend.event.service.EventService;
import com.tickitz.backend.exceptions.applicationException.ApplicationException;
import com.tickitz.backend.review.dto.CreateReviewRequestDto;
import com.tickitz.backend.review.dto.ReviewResponseDto;
import com.tickitz.backend.review.dto.UpdateReviewRequestDto;
import com.tickitz.backend.review.entity.Review;
import com.tickitz.backend.review.repository.ReviewRepository;
import com.tickitz.backend.review.service.ReviewService;
import com.tickitz.backend.users.service.UsersService;
import lombok.extern.java.Log;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Log
public class ReviewServiceImpl implements ReviewService {
  private final ReviewRepository reviewRepository;
  private final UsersService usersService;
  private final EventService eventService;

  public ReviewServiceImpl(ReviewRepository reviewRepository, UsersService usersService, EventService eventService) {
    this.reviewRepository = reviewRepository;
    this.usersService = usersService;
    this.eventService = eventService;
  }

  @Override
  public List<ReviewResponseDto> getAllReview(Long eventId) {
    if (eventId == null) {
      List<Review> result = reviewRepository.findAll();
      return result.stream()
              .map(this::mapToReviewResponseDto)
              .collect(Collectors.toList());
    }
    List<Review> result = reviewRepository.findAllByEventId(eventId);
    return result.stream()
            .map(this::mapToReviewResponseDto)
            .collect(Collectors.toList());
  }

  @Override
  public ReviewResponseDto getDetailReview(Long id) {
    Review detail = reviewRepository.findById(id).orElseThrow(() -> new ApplicationException("Review not exists"));
    return mapToReviewResponseDto(detail);
  }

  @Override
  public Review getDetail(Long id) {
    return reviewRepository.findById(id).orElseThrow(() -> new ApplicationException("Review not exists"));
  }

  @Override
  public ReviewResponseDto createReview(CreateReviewRequestDto createReviewRequestDto) {
    Review newReview = reviewRepository.save(createReviewRequestDto.toEntity(usersService, eventService));
    return mapToReviewResponseDto(newReview);
  }

  @Override
  public ReviewResponseDto updateReview(UpdateReviewRequestDto updateReviewRequestDto) {
    Review updateReview = getDetail(updateReviewRequestDto.getId());
    updateReview.setExperience(updateReviewRequestDto.getExperience());
    updateReview.setQuality(updateReviewRequestDto.getQuality());
    updateReview.setImprovement(updateReviewRequestDto.getImprovement());
    updateReview.setRating(updateReviewRequestDto.getRating());
    Review updated = reviewRepository.save(updateReview);
    return mapToReviewResponseDto(updated);
  }

  @Override
  public String deleteReview(Long id) {
    Review exists = getDetail(id);
    reviewRepository.deleteById(id);
    return "Delete Review Success";
  }

  public ReviewResponseDto mapToReviewResponseDto(Review review) {
    ReviewResponseDto response = new ReviewResponseDto();
    response.setId(review.getId());
    response.setExperience(review.getExperience());
    response.setQuality(review.getQuality());
    response.setImprovement(review.getImprovement());
    response.setRating(review.getRating());
    response.setUser(review.getUser());
    response.setEventId(review.getEvent().getId());
    response.setCreatedAt(review.getCreatedAt());
    response.setUpdatedAt(review.getUpdatedAt());
    response.setDeletedAt(review.getDeletedAt());
    return response;
  }
}
