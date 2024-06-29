package com.tickitz.backend.users.dto;

import com.tickitz.backend.users.entity.Users;
import jakarta.persistence.Column;
import lombok.Data;

@Data
public class ResponseUserDto {
  private Long id;
  private String username;
  private String email;
  private String role;
  @Column(name = "referral_code")
  private String referralCode;
  private Integer point;
}
