package com.tickitz.backend.auth.service;

import com.tickitz.backend.auth.dto.ResetPasswordRequestDto;
import org.springframework.security.core.Authentication;

public interface AuthService {
  String generateToken(Authentication authentication);
  String resetPassword(ResetPasswordRequestDto resetPasswordRequestDto);
  String logout();
}
