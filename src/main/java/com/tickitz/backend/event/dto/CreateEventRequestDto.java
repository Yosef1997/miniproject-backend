package com.tickitz.backend.event.dto;

import com.tickitz.backend.event.entity.Event;
import com.tickitz.backend.promotion.dto.CreatePromoRequestDto;
import com.tickitz.backend.promotion.entity.Promotion;
import com.tickitz.backend.ticket.dao.TicketDao;
import com.tickitz.backend.ticket.dto.CreateTicketRequestDto;
import com.tickitz.backend.users.entity.Users;
import lombok.Data;

import java.time.Instant;
import java.util.List;

@Data
public class CreateEventRequestDto {
  private String eventName;
  private String eventImage;
  private String category;
  private String location;
  private String venue;
  private String description;
  private String date;
  private String startTime;
  private String endTime;
  private Long userId;
  private List<CreateTicketRequestDto> tickets;
  private List<CreatePromoRequestDto> promotions;

  public Event toEntity(Users user) {
    Event newEvent = new Event();
    newEvent.setEventName(eventName);
    newEvent.setEventImage(eventImage);
    newEvent.setCategory(category);
    newEvent.setLocation(location);
    newEvent.setVenue(venue);
    newEvent.setDescription(description);
    newEvent.setDate(Instant.parse(date));
    newEvent.setStartTime(Instant.parse(startTime));
    newEvent.setEndTime(Instant.parse(endTime));
    newEvent.setUser(user);
    return newEvent;
  }


}
