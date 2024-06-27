package com.tickitz.backend.users.service;

import com.tickitz.backend.users.dto.RegisterDTO;
import com.tickitz.backend.users.entity.Users;

import java.util.List;

public interface UsersService {
  Users register(RegisterDTO requestRegister);

  List<Users> getAllUser();
}
