package com.tickitz.backend.referral.dto;

import jakarta.persistence.Column;
import lombok.Data;

@Data
public class ReferralRequestDto {
  private Long id;
  @Column(name = "referral_code")
  private String referralCode;
}
