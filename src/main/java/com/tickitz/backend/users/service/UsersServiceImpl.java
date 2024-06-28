package com.tickitz.backend.users.service;

import com.tickitz.backend.exceptions.applicationException.ApplicationException;
import com.tickitz.backend.users.dto.RegisterDto;
import com.tickitz.backend.users.entity.Users;
import com.tickitz.backend.users.repository.UsersRepository;
import lombok.extern.java.Log;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Log
public class UsersServiceImpl implements UsersService{
  private final UsersRepository usersRepository;
  private final PasswordEncoder passwordEncoder;

  public UsersServiceImpl(UsersRepository usersRepository, PasswordEncoder passwordEncoder) {
    this.usersRepository = usersRepository;
    this.passwordEncoder = passwordEncoder;
  }


  @Override
  public Users register(RegisterDto requestRegister) {
    Optional<Users> exists = usersRepository.findByEmail(requestRegister.getEmail());
    if (exists.isPresent()) {
      throw new ApplicationException("Email already exists");
    }
    Users newUser = requestRegister.toEntity();
    String encodePassword = passwordEncoder.encode(requestRegister.getPassword());
    newUser.setPassword(encodePassword);
    newUser.setRole(Users.Role.valueOf(requestRegister.getRole().name()));
    return usersRepository.save(newUser);
  }

  @Override
  public List<Users> getAllUser() {
    return usersRepository.findAll();
  }
}
