package com.tickitz.backend.promotion.dto;

import com.tickitz.backend.promotion.entity.Promotion;
import com.tickitz.backend.types.PromotionTypeEnum;
import lombok.Data;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Data
public class PromotionDto {
  private Long id;
  private String name;
  private PromotionTypeEnum type;
  private Integer usageLimit;
  private Double discount;
  private Instant expiredDate;
  private Long eventId;
}
