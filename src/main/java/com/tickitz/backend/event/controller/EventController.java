package com.tickitz.backend.event.controller;

import com.tickitz.backend.event.dto.RequestEventDto;
import com.tickitz.backend.event.entity.Event;
import com.tickitz.backend.event.service.EventService;
import com.tickitz.backend.response.Response;
import lombok.extern.java.Log;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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
  public ResponseEntity<Response<List<Event>>> getAllEvents() {
    return Response.successResponse("All events fetched", eventService.getAllEvents());
  }

  @GetMapping("/{id}")
  public ResponseEntity<Response<Optional<Event>>> getDetailEvent(@PathVariable Long id) {
    return Response.successResponse("Get Detail Event Success", eventService.getDetailEvent(id));
  }

  @PostMapping()
  public ResponseEntity<Response<Event>> createEvent(
          @RequestBody RequestEventDto requestEventDto) {
    return Response.successResponse("Create event success", eventService.createEvent(requestEventDto));
  }
}
