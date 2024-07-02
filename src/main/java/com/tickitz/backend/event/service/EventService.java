package com.tickitz.backend.event.service;

import com.tickitz.backend.event.dto.RequestEventDto;
import com.tickitz.backend.event.entity.Event;

import java.util.List;
import java.util.Optional;

public interface EventService {
  List<Event> getAllEvents();
  Optional<Event> getDetailEvent(Long id);
  Event createEvent(RequestEventDto requestEventDto);
  RequestEventDto updateEvent(RequestEventDto requestEventDto);
  String deleteEvent(Long eventId);
}
