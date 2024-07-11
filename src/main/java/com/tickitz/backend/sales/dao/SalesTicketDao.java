package com.tickitz.backend.sales.dao;

import java.time.Instant;

public interface SalesTicketDao {
  Long getEventId();
  String getEventName();
  Long getTotalTicket();
  Instant getDate();
}
