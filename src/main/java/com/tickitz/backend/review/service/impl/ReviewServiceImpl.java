package com.tickitz.backend.review.service.impl;

import com.tickitz.backend.event.service.EventService;
import com.tickitz.backend.exceptions.applicationException.ApplicationException;
import com.tickitz.backend.review.dao.ReviewDao;
import com.tickitz.backend.review.dto.CreateReviewRequestDto;
import com.tickitz.backend.review.dto.ReviewResponseDto;
import com.tickitz.backend.review.dto.UpdateReviewRequestDto;
import com.tickitz.backend.review.entity.Review;
import com.tickitz.backend.review.repository.ReviewRepository;
import com.tickitz.backend.review.service.ReviewService;
import com.tickitz.backend.users.service.UsersService;
import lombok.extern.java.Log;
import org.springframework.stereotype.Service;

import java.util.List;

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
  public List<ReviewDao> getAllReview(Long eventId) {
    if (eventId == null) {
      return reviewRepository.findAllReview();
    }
    return reviewRepository.findAllReviewByEventId(eventId);
  }

  @Override
  public ReviewResponseDto getDetailReview(Long id) {
    Review detail = reviewRepository.findById(id).orElseThrow(() -> new ApplicationException("Review not exists"));

    ReviewResponseDto response = new ReviewResponseDto();
    response.setId(detail.getId());
    response.setExperience(detail.getExperience());
    response.setQuality(detail.getQuality());
    response.setImprovement(detail.getImprovement());
    response.setRating(detail.getRating());
    response.setUserId(detail.getUser().getId());
    response.setEventId(detail.getEvent().getId());
    response.setCreatedAt(detail.getCreatedAt());
    response.setUpdatedAt(detail.getUpdatedAt());
    response.setDeletedAt(detail.getDeletedAt());
    return response;
  }

  @Override
  public Review getDetail(Long id) {
    return reviewRepository.findById(id).orElseThrow(() -> new ApplicationException("Review not exists"));
  }

  @Override
  public ReviewResponseDto createReview(CreateReviewRequestDto createReviewRequestDto) {
    Review newReview = reviewRepository.save(createReviewRequestDto.toEntity(usersService, eventService));
    return createReviewRequestDto.mapToReviewResponseDto(newReview);
  }

  @Override
  public ReviewResponseDto updateReview(UpdateReviewRequestDto updateReviewRequestDto) {
    Review updateReview = getDetail(updateReviewRequestDto.getId());
    updateReview.setExperience(updateReviewRequestDto.getExperience());
    updateReview.setQuality(updateReviewRequestDto.getQuality());
    updateReview.setImprovement(updateReviewRequestDto.getImprovement());
    updateReview.setRating(updateReviewRequestDto.getRating());
    Review updated = reviewRepository.save(updateReview);
    return updateReviewRequestDto.mapToReviewResponseDto(updated);
  }

  @Override
  public String deleteReview(Long id) {
    Review exists = getDetail(id);
    reviewRepository.deleteById(id);
    return "Delete Review Success";
  }
}
