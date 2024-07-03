package com.tickitz.backend.users.dto;

import com.tickitz.backend.referral.dto.ReferralResponseDto;
import com.tickitz.backend.referral.entity.Referral;
import com.tickitz.backend.users.entity.Users;
import lombok.Data;

@Data
public class UpdateUserResponseDto {
  private Long id;
  private String username;
  private String email;
  private String avatar;
  private String role;
  private Long point;
  private ReferralResponseDto referral;
}
