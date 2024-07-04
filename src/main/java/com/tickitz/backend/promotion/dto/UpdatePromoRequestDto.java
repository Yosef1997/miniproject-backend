package com.tickitz.backend.promotion.dto;

import com.tickitz.backend.types.PromotionTypeEnum;
import lombok.Data;

import java.time.Instant;

@Data
public class UpdatePromoRequestDto {
  private Long id;
  private String name;
  private Integer usageLimit;
  private Double discount;
  private Instant expiredDate;
  private Long eventId;
}
