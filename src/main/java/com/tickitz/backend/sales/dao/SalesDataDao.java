package com.tickitz.backend.sales.dao;

import java.time.Instant;

public interface SalesDataDao {
  Long getEventId();
  String getEventName();
  Long getTotalSales();
  Instant getDate();
}
