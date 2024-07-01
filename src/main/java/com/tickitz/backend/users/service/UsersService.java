package com.tickitz.backend.users.service;

import com.tickitz.backend.users.dto.RegisterRequestDto;
import com.tickitz.backend.users.dto.ResponseUserDto;
import com.tickitz.backend.users.entity.Users;

import java.util.List;

public interface UsersService {
  ResponseUserDto register(RegisterRequestDto requestRegister);
  List<Users> getAllUser();
  ResponseUserDto getProfile();

}
