package com.tickitz.backend.point.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class PointRequestDto {
  private Long id;
  private Long point;
}
