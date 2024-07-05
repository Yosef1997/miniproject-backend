package com.tickitz.backend.review.dao;

public interface ReviewDao {
  Long getId();
  String getExperience();
  String getQuality();
  String getImprovement();
  Double getRating();
  Long getUserId();
  Long getEventId();
}
