package com.tickitz.backend.point.dto;

import com.tickitz.backend.point.entity.Point;
import com.tickitz.backend.users.entity.Users;
import com.tickitz.backend.users.service.UsersService;
import lombok.Data;

@Data
public class CreatePointRequestDto {
  private Long point;
  private Long userId;

  public Point toEntity(Users user) {
    Point newPoint = new Point();
    newPoint.setPoint(point);
    newPoint.setUser(user);
    return newPoint;
  }
}
