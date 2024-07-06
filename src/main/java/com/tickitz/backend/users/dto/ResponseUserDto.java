package com.tickitz.backend.users.dto;

import lombok.Data;

@Data
public class ResponseUserDto {
  private Long id;
  private String username;
  private String avatar;
  private String email;
  private String role;
  private String referralCode;
  private Long point;
  private Object referralVoucher;
}
