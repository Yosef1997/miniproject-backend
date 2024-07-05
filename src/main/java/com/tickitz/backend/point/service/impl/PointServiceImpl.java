package com.tickitz.backend.point.service.impl;

import com.tickitz.backend.exceptions.applicationException.ApplicationException;
import com.tickitz.backend.point.dto.CreatePointRequestDto;
import com.tickitz.backend.point.dto.PointResponseDto;
import com.tickitz.backend.point.entity.Point;
import com.tickitz.backend.point.repository.PointRepository;
import com.tickitz.backend.point.service.PointService;
import com.tickitz.backend.users.entity.Users;
import com.tickitz.backend.users.repository.UsersRepository;
import lombok.extern.java.Log;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Log
public class PointServiceImpl implements PointService {
  private final PointRepository pointRepository;
  private final UsersRepository usersRepository;

  public PointServiceImpl (PointRepository pointRepository, UsersRepository usersRepository) {
    this.pointRepository = pointRepository;
    this.usersRepository = usersRepository;
  }


  @Override
  public List<PointResponseDto> getAllPoints() {
   List<Point> result = pointRepository.findAll();
    return result.stream().map(this::mapToPointResponseDto).collect(Collectors.toList());
  }

  @Override
  public PointResponseDto createPoints(CreatePointRequestDto createPointRequestDto) {
    Users user = usersRepository.findById(createPointRequestDto.getUserId()).orElseThrow(()-> new ApplicationException("User not exists"));
    Point saved = pointRepository.save(createPointRequestDto.toEntity(user));
    return mapToPointResponseDto(saved);
  }

  @Override
  public Long getPointUser(Long id) {
    Instant expiredDay = Instant.now().minus(90, ChronoUnit.DAYS);
    return pointRepository.getUserTotalPoints(id, expiredDay);
  }

  public PointResponseDto mapToPointResponseDto(Point point) {
    PointResponseDto response = new PointResponseDto();
    response.setId(point.getId());
    response.setPoint(point.getPoint());
    response.setUserId(point.getUser().getId());
    return response;
  }
}
