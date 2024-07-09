package com.tickitz.backend.order.dao;

import java.time.Instant;

public interface SalesDao {
  Long getId();
  Long getEventId();
  String getEventName();
  Long getTotalPrice();
  Instant getDate();
}
