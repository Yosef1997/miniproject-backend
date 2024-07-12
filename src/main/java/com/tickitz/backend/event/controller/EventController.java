package com.tickitz.backend.event.controller;

import com.tickitz.backend.event.dto.CreateEventRequestDto;
import com.tickitz.backend.event.dto.EventResponseDto;
import com.tickitz.backend.event.dto.UpdateEventRequestDto;
import com.tickitz.backend.event.entity.Event;
import com.tickitz.backend.event.service.EventService;
import com.tickitz.backend.response.Response;
import lombok.extern.java.Log;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/event")
@Validated
@Log
public class EventController {
  private final EventService eventService;

  public EventController(EventService eventService) {
    this.eventService = eventService;
  }

  @GetMapping
  public ResponseEntity<Response<Page<Event>>> getAllEvents(@RequestParam(required = false) String eventName, @RequestParam(required = false) String location, @RequestParam(required = false) String category, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "20") int size) {
    Pageable pageable = PageRequest.of(page, size);
    return Response.successResponse("All events fetched", eventService.getAllEvents( pageable, eventName, location, category));
  }

  @GetMapping("/{id}")
  public ResponseEntity<Response<EventResponseDto>> getDetailEvent(@PathVariable Long id) {
    return Response.successResponse("Get Detail Event Success", eventService.getDetailEvent(id));
  }

  @PostMapping
  public ResponseEntity<Response<EventResponseDto>> createEvent(@RequestBody CreateEventRequestDto createEventRequestDto) {
    return Response.successResponse("Create event success", eventService.createEvent(createEventRequestDto));
  }

  @PutMapping
  public ResponseEntity<Response<EventResponseDto>> updateEvent(@RequestBody UpdateEventRequestDto updateEventRequestDto) {
    return Response.successResponse("Update event success", eventService.updateEvent(updateEventRequestDto));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Response<String>> deleteEvent(@PathVariable Long id) {
    return Response.successResponse("Delete Event Success", eventService.deleteEvent(id));
  }
}
