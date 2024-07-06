package com.tickitz.backend.referral.dto;

import com.tickitz.backend.referral.entity.Referral;
import jakarta.validation.constraints.Min;
import lombok.Data;

@Data
public class UpdateReferralRequestDto {
  private Long id;
  @Min(value = 0)
  private Integer discountPercentage;
  private Boolean status;

  public Referral toEntity(Referral referral) {
    referral.setId(id);
    referral.setDiscountPercentage(discountPercentage);
    referral.setStatus(status);
    return referral;
  }
}
