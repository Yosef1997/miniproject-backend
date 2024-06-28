package com.tickitz.backend.users.service;

import com.tickitz.backend.users.dto.RegisterDto;
import com.tickitz.backend.users.entity.Users;

import java.util.List;

public interface UsersService {
  Users register(RegisterDto requestRegister);
  List<Users> getAllUser();
  Users getProfile();

}
