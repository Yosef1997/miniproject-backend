package com.tickitz.backend.promotion.dto;

import com.tickitz.backend.promotion.entity.Promotion;
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

  public Promotion toEntity(Promotion promotion) {
    promotion.setId(id);
    promotion.setName(name);
    promotion.setUsageLimit(usageLimit);
    promotion.setDiscount(discount);
    promotion.setExpiredDate(expiredDate);
    return promotion;
  }
}
