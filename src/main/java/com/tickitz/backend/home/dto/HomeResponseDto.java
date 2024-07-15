package com.tickitz.backend.home.dto;

import com.tickitz.backend.event.dto.EventResponseDto;
import lombok.Data;

import java.util.List;

@Data
public class HomeResponseDto {
  List<EventResponseDto> popular;
  List<EventResponseDto> upcoming;
}
