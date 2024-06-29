package com.tickitz.backend.users.service;

import com.tickitz.backend.auth.helpers.Claims;
import com.tickitz.backend.exceptions.applicationException.ApplicationException;
import com.tickitz.backend.users.dto.RegisterDto;
import com.tickitz.backend.users.entity.Users;
import com.tickitz.backend.users.repository.UsersRepository;
import lombok.extern.java.Log;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

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
    newUser.setReferralCode(UUID.randomUUID().toString().replace("-", "").substring(0, 10));
    if (requestRegister.getRole() == Users.Role.CUSTOMER && !Objects.equals(requestRegister.getReferral(), "")) {
      log.info("get voucher referral 10%");
    }
    return usersRepository.save(newUser);
  }

  @Override
  public List<Users> getAllUser() {
    return usersRepository.findAll();
  }

  @Override
  public Users getProfile() {
    var claims = Claims.getClaimsFromJwt();
    var email = (String) claims.get("sub");
    return usersRepository.findByEmail(email).orElseThrow(()->new ApplicationException("Profile not found"));
  }

}
