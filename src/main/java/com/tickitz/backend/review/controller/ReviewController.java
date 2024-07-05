package com.tickitz.backend.review.controller;

import com.tickitz.backend.response.Response;
import com.tickitz.backend.review.dto.CreateReviewRequestDto;
import com.tickitz.backend.review.dto.ReviewResponseDto;
import com.tickitz.backend.review.dto.UpdateReviewRequestDto;
import com.tickitz.backend.review.service.ReviewService;
import lombok.extern.java.Log;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/review")
@Validated
@Log
public class ReviewController {
  private final ReviewService reviewService;

  public ReviewController(ReviewService reviewService) {
    this.reviewService = reviewService;
  }

  @GetMapping
  public ResponseEntity<Response<List<ReviewResponseDto>>> getAllReviews(@RequestParam(name = "eventId", required = false) Long eventId) {
    return Response.successResponse("All Review Fetched", reviewService.getAllReview(eventId));
  }

  @GetMapping("/{id}")
  public ResponseEntity<Response<ReviewResponseDto>> getDetailReview(@PathVariable Long id) {
    return Response.successResponse("Detail review fetched", reviewService.getDetailReview(id));
  }

  @PostMapping
  public ResponseEntity<Response<ReviewResponseDto>> createReview(@RequestBody CreateReviewRequestDto createReviewRequestDto) {
    return Response.successResponse("Create Review Success", reviewService.createReview(createReviewRequestDto));
  }

  @PutMapping
  public ResponseEntity<Response<ReviewResponseDto>> updateReview(@RequestBody UpdateReviewRequestDto updateReviewRequestDto) {
    return Response.successResponse("Update Review Success", reviewService.updateReview(updateReviewRequestDto));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Response<String>> deleteReview(@PathVariable Long id) {
    return Response.successResponse("Delete Review Success", reviewService.deleteReview(id));
  }
}
