package com.tickitz.backend.users.service;

import com.tickitz.backend.users.dao.ResponseUserDao;
import com.tickitz.backend.users.dto.RegisterRequestDto;
import com.tickitz.backend.users.dto.ResponseUserDto;
import com.tickitz.backend.users.dto.UpdateUserRequestDto;
import com.tickitz.backend.users.dto.UpdateUserResponseDto;
import com.tickitz.backend.users.entity.Users;

import java.util.List;

public interface UsersService {
  ResponseUserDto register(RegisterRequestDto requestRegister);
  List<ResponseUserDao> getAllUser();
  ResponseUserDto getProfile();
  UpdateUserResponseDto updateUser(UpdateUserRequestDto updateUserRequestDto);
  ResponseUserDto getDetailUser(String email);
  Users getDetailUserId(Long id);
}
