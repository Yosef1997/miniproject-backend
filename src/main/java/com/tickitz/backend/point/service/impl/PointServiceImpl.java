package com.tickitz.backend.point.service.impl;

import com.tickitz.backend.exceptions.applicationException.ApplicationException;
import com.tickitz.backend.point.dao.ResponsePointDao;
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
  public List<ResponsePointDao> getAllPoints() {
   return   pointRepository.findAllWithUser();
  }

  @Override
  public PointResponseDto createPoints(Long id) {
    Users user = usersRepository.findById(id).orElseThrow(()-> new ApplicationException("User not exists"));
    Point point = new Point();
    point.setPoint(10000);
    point.setUser(user);

    pointRepository.save(point);

    PointResponseDto responseDto = new PointResponseDto();

    responseDto.setId(pointRepository.save(point).getId());
    responseDto.setPoint(point.getPoint());
    responseDto.setUserId(point.getUser().getId());

    return responseDto;
  }

  @Override
  public Long getPointUser(Long id) {
    Instant expiredDay = Instant.now().minus(90, ChronoUnit.DAYS);
    log.info(expiredDay.toString());
    return pointRepository.getUserTotalPoints(id, expiredDay);
  }
}
