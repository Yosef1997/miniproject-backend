package com.tickitz.backend.point.dto;

import jakarta.persistence.Column;
import lombok.Data;

@Data
public class PointResponseDto {
  private Long Id;
  private Integer point;
  @Column(name = "user_id")
  private Long userId;
}
