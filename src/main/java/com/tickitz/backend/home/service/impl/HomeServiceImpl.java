package com.tickitz.backend.home.service.impl;

import com.tickitz.backend.event.dto.EventResponseDto;
import com.tickitz.backend.event.service.EventService;
import com.tickitz.backend.home.dto.HomeResponseDto;
import com.tickitz.backend.home.service.HomeService;
import lombok.extern.java.Log;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Log
public class HomeServiceImpl implements HomeService {
  private final EventService eventService;

  public HomeServiceImpl(EventService eventService) {
    this.eventService = eventService;
  }

  @Override
  public HomeResponseDto getHome() {
    Pageable pageable = PageRequest.of(0, 6);
    List<EventResponseDto> popular = eventService.getAllEvents(pageable,null,null,null,null,null).getContent();
    List<EventResponseDto> upcoming = eventService.getAllEvents(pageable,null,null,null,null,"true").getContent();
    HomeResponseDto response = new HomeResponseDto();
    response.setPopular(popular);
    response.setUpcoming(upcoming);
    return response;
  }
}
