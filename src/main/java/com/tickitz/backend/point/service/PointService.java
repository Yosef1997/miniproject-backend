package com.tickitz.backend.point.service;


import com.tickitz.backend.point.dao.ResponsePointDao;
import com.tickitz.backend.point.dto.PointResponseDto;
import com.tickitz.backend.point.entity.Point;

import java.util.List;

public interface PointService {
  List<ResponsePointDao> getAllPoints();
  PointResponseDto createPoints(Long id);
  Long getPointUser(Long id);
}