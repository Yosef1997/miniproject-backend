package com.tickitz.backend.event.service;

import com.tickitz.backend.event.dto.CreateEventRequestDto;
import com.tickitz.backend.event.dto.EventResponseDto;
import com.tickitz.backend.event.dto.UpdateEventRequestDto;
import com.tickitz.backend.event.entity.Event;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface EventService {
  Page<EventResponseDto> getAllEvents(Pageable pageable, String eventName, String location, String category);
  EventResponseDto getDetailEvent(Long id);
  Event getDetail(Long id);
  EventResponseDto createEvent(CreateEventRequestDto createEventRequestDto);
  EventResponseDto updateEvent(UpdateEventRequestDto updateEventRequestDto);
  String deleteEvent(Long eventId);
}
