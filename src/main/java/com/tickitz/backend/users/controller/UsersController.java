package com.tickitz.backend.users.controller;

import com.tickitz.backend.response.Response;
import com.tickitz.backend.users.dao.ResponseUserDao;
import com.tickitz.backend.users.dto.RegisterRequestDto;
import com.tickitz.backend.users.dto.ResponseUserDto;
import com.tickitz.backend.users.dto.UpdateUserRequestDto;
import com.tickitz.backend.users.dto.UpdateUserResponseDto;
import com.tickitz.backend.users.entity.Users;
import com.tickitz.backend.users.service.UsersService;
import lombok.extern.java.Log;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/users")
@Validated
@Log
public class UsersController {
  private final UsersService usersService;

  public UsersController(UsersService usersService) {
    this.usersService = usersService;
  }

  @GetMapping()
  public ResponseEntity<Response<List<ResponseUserDao>>> getAllUsers() {
    return Response.successResponse("All registered users", usersService.getAllUser());
  }

  @PostMapping("/register")
  public ResponseEntity<?> register(@RequestBody RegisterRequestDto registerDTO) {
    return Response.successResponse("User register success", usersService.register(registerDTO));
  }

  @GetMapping("/profile")
  public ResponseEntity<Response<Object>> getProfile() {
    return Response.successResponse("Profile fetch success", usersService.getProfile());
  }

  @PutMapping("/profile")
  public ResponseEntity<Response<UpdateUserResponseDto>> updateUser(@RequestBody UpdateUserRequestDto updateUserRequestDto) {
    return Response.successResponse("Update User Success", usersService.updateUser(updateUserRequestDto));
  }

  @GetMapping("/detail/{email}")
  public ResponseEntity<Response<ResponseUserDto>> getDetailUser(@PathVariable String email) {
    return Response.successResponse("Get Detail User Success", usersService.getDetailUser(email));
  }

}
