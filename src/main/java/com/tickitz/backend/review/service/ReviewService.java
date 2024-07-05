package com.tickitz.backend.review.service;

import com.tickitz.backend.review.dto.CreateReviewRequestDto;
import com.tickitz.backend.review.dto.ReviewResponseDto;
import com.tickitz.backend.review.dto.UpdateReviewRequestDto;
import com.tickitz.backend.review.entity.Review;

import java.util.List;

public interface ReviewService {
  List<ReviewResponseDto> getAllReview(Long eventId);
  ReviewResponseDto getDetailReview(Long id);
  Review getDetail(Long id);
  ReviewResponseDto createReview(CreateReviewRequestDto createReviewRequestDto);
  ReviewResponseDto updateReview(UpdateReviewRequestDto updateReviewRequestDto);
  String deleteReview(Long id);
}
