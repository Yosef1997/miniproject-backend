package com.tickitz.backend.auth.service;

import org.springframework.security.core.Authentication;

public interface AuthService {
  String generateToken(Authentication authentication);
  String resetPassword(String email);
}
