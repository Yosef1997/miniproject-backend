package com.tickitz.backend.utils;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class DateConverter {
  public static Instant convertToInstant(String dateString) {
    LocalDate localDate = LocalDate.parse(dateString, DateTimeFormatter.ISO_LOCAL_DATE);
    return localDate.atStartOfDay(ZoneId.systemDefault()).toInstant();
  }
}
