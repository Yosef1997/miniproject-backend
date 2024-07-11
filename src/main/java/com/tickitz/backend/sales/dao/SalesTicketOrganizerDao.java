package com.tickitz.backend.sales.dao;

import java.time.Instant;

public interface SalesTicketOrganizerDao {
  Long getOrganizerId();
  Long getTotalTicket();
  Instant getDate();
}
