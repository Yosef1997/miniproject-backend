package com.tickitz.backend.users.dto;


import com.tickitz.backend.users.entity.Users;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class RegisterRequestDto {
  @NotNull
  @NotBlank(message = "Email is required")
  private String email;

  @NotNull
  @NotBlank(message = "Password is required")
  private String password;

  @NotNull
  @Enumerated(EnumType.STRING)
  private Users.Role role;

  @NotNull
  private String referral;

  public Users toEntity() {
    Users user = new Users();
    user.setEmail(email);
    user.setPassword(password);
    user.setRole(role);
    return user;
  }
}
