package com.tickitz.backend.sales.dao;

import java.time.Instant;
import java.time.LocalDate;

public interface SalesOrganizerDao {
  Long getOrganizerId();
  Long getTotalSales();
  Instant getDate();
}
