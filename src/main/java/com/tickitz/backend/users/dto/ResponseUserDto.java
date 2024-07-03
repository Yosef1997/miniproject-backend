package com.tickitz.backend.users.dto;

import com.tickitz.backend.referral.dto.ReferralResponseDto;
import com.tickitz.backend.users.entity.Users;
import jakarta.persistence.Column;
import jakarta.validation.constraints.Min;
import lombok.Data;

@Data
public class ResponseUserDto {
  private Long id;
  private String username;
  private String avatar;
  private String email;
  private String role;
  private String referralCode;
  @Min(value = 0)
  private Long point;
  private Object referralVoucher;
}
