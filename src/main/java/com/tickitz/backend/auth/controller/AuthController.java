package com.tickitz.backend.auth.controller;

import com.nimbusds.jose.proc.SecurityContext;
import com.tickitz.backend.auth.dto.LoginRequestDto;
import com.tickitz.backend.auth.dto.LoginResponseDto;
import com.tickitz.backend.auth.dto.ResetPasswordRequestDto;
import com.tickitz.backend.auth.entity.UserAuth;
import com.tickitz.backend.auth.service.AuthService;
import com.tickitz.backend.response.Response;
import lombok.extern.java.Log;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@Log
public class AuthController {
  private final AuthService authService;

  private final AuthenticationManager authenticationManager;

  public AuthController(AuthService authService, AuthenticationManager authenticationManager) {
    this.authService = authService;
    this.authenticationManager = authenticationManager;
  }

  @PostMapping("/login")
  public ResponseEntity<?> login(@RequestBody LoginRequestDto loginRequestDto) throws IllegalAccessException {
    Authentication authentication =
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    loginRequestDto.getEmail(),
                    loginRequestDto.getPassword()
            ));
    SecurityContextHolder.getContext().setAuthentication(authentication);
    UserAuth userDetails = (UserAuth) authentication.getPrincipal();
    String token = authService.generateToken(authentication);

    LoginResponseDto loginResponseDto = new LoginResponseDto();
    loginResponseDto.setMessage("User Logged in successfully");
    loginResponseDto.setToken(token);
    return Response.successResponse(loginResponseDto.getMessage(), loginResponseDto);
  }

  @PostMapping("/forgot-password")
  public ResponseEntity<Response<Object>> forgotPassword (@Validated @RequestBody ResetPasswordRequestDto resetPasswordRequestDto) {
    return Response.successResponse(authService.resetPassword(resetPasswordRequestDto));
  }
}
