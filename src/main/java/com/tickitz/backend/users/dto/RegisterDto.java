package com.tickitz.backend.users.dto;


import com.tickitz.backend.users.entity.Users;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class RegisterDto {

  @NotBlank(message = "Email is required")
  private String email;

  @NotBlank(message = "Password is required")
  private String password;


  @Enumerated(EnumType.STRING)
  private Users.Role role;

  public Users toEntity() {
    Users user = new Users();
    user.setEmail(email);
    user.setPassword(password);
    user.setRole(role);
    return user;
  }
}
