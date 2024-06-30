package com.tickitz.backend.referral.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ReferralResponseDto {
  private Long id;
  @NotBlank(message = "Voucher name is required")
  private String voucherName;
  @Min(value = 0)
  private Integer discountPercentage;
  private Long userId;
  private Boolean status;

}
