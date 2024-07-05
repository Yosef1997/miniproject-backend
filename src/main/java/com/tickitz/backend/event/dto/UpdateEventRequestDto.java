package com.tickitz.backend.event.dto;

import com.tickitz.backend.event.entity.Event;
import com.tickitz.backend.promotion.dto.CreatePromoRequestDto;
import com.tickitz.backend.ticket.dto.CreateTicketRequestDto;
import lombok.Data;

import java.time.Instant;
import java.util.List;

@Data
public class UpdateEventRequestDto {
  private Long id;
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

  public Event toEntity(Event existsEvent) {
    existsEvent.setId(id);
    existsEvent.setEventName(eventName);
    existsEvent.setEventImage(eventImage);
    existsEvent.setCategory(category);
    existsEvent.setLocation(location);
    existsEvent.setVenue(venue);
    existsEvent.setDescription(description);
    existsEvent.setDate(Instant.parse(date));
    existsEvent.setStartTime(Instant.parse(startTime));
    existsEvent.setEndTime(Instant.parse(endTime));
    return existsEvent;
  }
}
