package com.tickitz.backend.ticket.dao;

public interface TicketDao {
  Long getId();
  String getName();
  Integer getSeats();
  Long getPrice();
  Long getEventId();
}
