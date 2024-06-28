package com.tickitz.backend.users.controller;

import com.tickitz.backend.response.Response;
import com.tickitz.backend.users.dto.RegisterDto;
import com.tickitz.backend.users.entity.Users;
import com.tickitz.backend.users.service.UsersService;
import lombok.extern.java.Log;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/users")
@Log
public class UsersController {
  private final UsersService usersService;

  public UsersController(UsersService usersService) {
    this.usersService = usersService;
  }

  @GetMapping()
  public ResponseEntity<Response<List<Users>>> getAllUsers() {
    return Response.successResponse("All registered users", usersService.getAllUser());
  }

  @PostMapping("/register")
  public ResponseEntity<?> register(@Validated @RequestBody RegisterDto registerDTO) {
    return Response.successResponse("User register success", usersService.register(registerDTO));
  }

  @GetMapping("/profile")
  public ResponseEntity<Response<Object>> getProfile() {
    return Response.successResponse("Profile fetch success", usersService.getProfile());
  }
}
