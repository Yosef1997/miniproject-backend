package com.tickitz.backend.point.service;


import com.tickitz.backend.point.dto.CreatePointRequestDto;
import com.tickitz.backend.point.dto.PointResponseDto;

import java.util.List;

public interface PointService {
  List<PointResponseDto> getAllPoints();
  PointResponseDto createPoints(CreatePointRequestDto createPointRequestDto);
  Long getPointUser(Long id);
}
