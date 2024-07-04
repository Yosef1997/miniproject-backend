package com.tickitz.backend.promotion.dao;

import java.time.Instant;

public interface PromotionDao {
  Long getId();
  String getName();
  String getType();
  Integer getUsageLimit();
  Double getDiscount();
  Instant getExpiredDate();
  Long getEventId();
}
