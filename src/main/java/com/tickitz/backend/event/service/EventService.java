package com.tickitz.backend.event.service;

import com.tickitz.backend.event.dto.EventResponseDto;
import com.tickitz.backend.event.dto.CreateEventRequestDto;
import com.tickitz.backend.event.dto.UpdateEventRequestDto;
import com.tickitz.backend.event.entity.Event;

import java.util.List;

public interface EventService {
  List<Event> getAllEvents();
  EventResponseDto getDetailEvent(Long id);
  EventResponseDto createEvent(CreateEventRequestDto createEventRequestDto);
  EventResponseDto updateEvent(UpdateEventRequestDto updateEventRequestDto);
  String deleteEvent(Long eventId);
}
