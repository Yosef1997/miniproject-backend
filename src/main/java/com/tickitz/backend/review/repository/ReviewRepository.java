package com.tickitz.backend.review.repository;


import com.tickitz.backend.review.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {
  List<Review> findAllByEventId(Long eventId);
}