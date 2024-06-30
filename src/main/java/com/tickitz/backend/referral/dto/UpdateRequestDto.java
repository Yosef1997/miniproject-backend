package com.tickitz.backend.referral.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UpdateRequestDto {
  private Long id;
  private String voucherName;
  @Min(value = 0)
  private Integer discountPercentage;
  private Boolean status;
}
