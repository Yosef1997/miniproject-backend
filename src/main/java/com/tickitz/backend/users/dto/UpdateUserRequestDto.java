package com.tickitz.backend.users.dto;

import com.tickitz.backend.users.entity.Users;
import lombok.Data;

@Data
public class UpdateUserRequestDto {
  private String username;
  private String email;
  private String password;
  private String avatar;
  private String phone;

  public Users toEntity(Users user){
    user.setUsername(username);
    user.setEmail(email);
    user.setAvatar(avatar);
    user.setPhone(phone);
    return user;
  }
}
