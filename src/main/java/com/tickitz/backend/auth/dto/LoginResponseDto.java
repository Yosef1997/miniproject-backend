package com.tickitz.backend.auth.dto;

import com.tickitz.backend.users.entity.Users;
import lombok.Data;

@Data
public class LoginResponseDto {
  private Users user;
  private String message;
  private String token;
}
