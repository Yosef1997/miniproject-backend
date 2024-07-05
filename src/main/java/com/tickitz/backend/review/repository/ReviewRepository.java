package com.tickitz.backend.review.repository;

import com.tickitz.backend.review.dao.ReviewDao;
import com.tickitz.backend.review.entity.Review;
import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {
  @Query(value = "SELECT r.id as id, r.experience as experience, r.quality as quality, r.improvement as improvement, " +
              "r.rating as rating, u.id as userId, e.id as eventId FROM Review r LEFT JOIN r.user u LEFT JOIN r.event e")
  List<ReviewDao> findAllReview();
  @Query(value = "SELECT r.id as id, r.experience as experience, r.quality as quality, r.improvement as improvement, " +
          "r.rating as rating, u.id as userId, e.id as eventId FROM Review r LEFT JOIN r.user u LEFT JOIN r.event e " +
          "WHERE e.id = :eventId GROUP BY r.id, e.id, u.id")
  List<ReviewDao> findAllReviewByEventId(@Param("eventId") Long eventId);
}
