package com.tickitz.backend.referral.dto;

import com.tickitz.backend.referral.entity.Referral;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ReferralResponseDto {
  private Long id;
  private Integer discountPercentage;
  private Long userId;
  private Boolean status;
}
