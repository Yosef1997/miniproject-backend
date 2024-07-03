package com.tickitz.backend.users.dto;

import lombok.Data;

@Data
public class UpdateUserRequestDto {
  private String username;
  private String email;
  private String password;
  private String avatar;
}
