package com.tickitz.backend.promotion.dto;

import com.tickitz.backend.types.PromotionTypeEnum;
import lombok.Data;

import java.time.Instant;

@Data
public class PromoResponseDto {
  private Long id;
  private String name;
  private PromotionTypeEnum type;
  private Integer usageLimit;
  private Double discount;
  private Instant expiredDate;
  private Long eventId;
}
