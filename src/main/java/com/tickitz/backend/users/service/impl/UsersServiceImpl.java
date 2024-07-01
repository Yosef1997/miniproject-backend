package com.tickitz.backend.users.service.impl;

import com.tickitz.backend.auth.helpers.Claims;
import com.tickitz.backend.exceptions.applicationException.ApplicationException;
import com.tickitz.backend.point.dto.PointRequestDto;
import com.tickitz.backend.point.service.PointService;
import com.tickitz.backend.referral.dto.ReferralResponseDto;
import com.tickitz.backend.referral.entity.Referral;
import com.tickitz.backend.referral.service.ReferralService;
import com.tickitz.backend.users.dto.RegisterRequestDto;
import com.tickitz.backend.users.dto.ResponseUserDto;
import com.tickitz.backend.users.entity.Users;
import com.tickitz.backend.users.repository.UsersRepository;
import com.tickitz.backend.users.service.UsersService;
import lombok.extern.java.Log;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@Service
@Log
public class UsersServiceImpl implements UsersService {
  private final UsersRepository usersRepository;
  private final PasswordEncoder passwordEncoder;
  private final PointService pointService;
  private final ReferralService referralService;

  public UsersServiceImpl(UsersRepository usersRepository, PasswordEncoder passwordEncoder, PointService pointService, ReferralService referralService) {
    this.usersRepository = usersRepository;
    this.passwordEncoder = passwordEncoder;
    this.pointService = pointService;
    this.referralService = referralService;
  }


  @Override
  public ResponseUserDto register(RegisterRequestDto requestRegister) {
    Optional<Users> exists = usersRepository.findByEmail(requestRegister.getEmail());
    if (exists.isPresent()) {
      throw new ApplicationException("Email already exists");
    }
    Users newUser = requestRegister.toEntity();
    String encodePassword = passwordEncoder.encode(requestRegister.getPassword());
    newUser.setPassword(encodePassword);
    newUser.setRole(Users.Role.valueOf(requestRegister.getRole().name()));

    if (requestRegister.getRole() == Users.Role.ORGANIZER) {
      newUser.setReferralCode("");
    } else {
      newUser.setReferralCode(UUID.randomUUID().toString().replace("-", "").substring(0, 10));
    }

    Users savedUser = usersRepository.save(newUser);

    ReferralResponseDto referral = new ReferralResponseDto();

    if (requestRegister.getRole() == Users.Role.CUSTOMER && !Objects.equals(requestRegister.getReferral(), "")) {
      Optional<Users> referrer = usersRepository.findByReferralCode(requestRegister.getReferral());
      if (referrer.isPresent()) {
        var savedReferral = referralService.createReferral(savedUser.getId());
        referral.setId(savedUser.getId());
        referral.setVoucherName(savedReferral.getVoucherName());
        referral.setDiscountPercentage(savedReferral.getDiscountPercentage());
        referral.setStatus(savedReferral.getStatus());
        referral.setUserId(savedReferral.getUserId());

        PointRequestDto pointRequestDto = new PointRequestDto();
        pointRequestDto.setId(referrer.get().getId());
        pointRequestDto.setPoint(10000L);
        pointService.createPoints(pointRequestDto);
      }
    }

    ResponseUserDto responseUserDto = new ResponseUserDto();
    responseUserDto.setId(savedUser.getId());
    responseUserDto.setUsername(savedUser.getUsername());
    responseUserDto.setEmail(savedUser.getEmail());
    responseUserDto.setRole(savedUser.getRole().name());
    responseUserDto.setReferralCode(savedUser.getReferralCode());
    if(savedUser.getRole() == Users.Role.ORGANIZER) {
      responseUserDto.setPoint(0L);
    } else {
      responseUserDto.setPoint(pointService.getPointUser(savedUser.getId()));
    }
    if (referral.getId() == null) {
      responseUserDto.setReferralVoucher(null);
    } else {
      responseUserDto.setReferralVoucher(referral);
    }
    return responseUserDto;
  }

  @Override
  public List<Users> getAllUser() {
    return usersRepository.findAll();
  }

  @Override
  public ResponseUserDto getProfile() {
    var claims = Claims.getClaimsFromJwt();
    var email = (String) claims.get("sub");
    Users user = usersRepository.findByEmail(email).orElseThrow(()->new ApplicationException("Profile not found"));
    ResponseUserDto responseUserDto = new ResponseUserDto();
    responseUserDto.setId(user.getId());
    responseUserDto.setUsername(user.getUsername());
    responseUserDto.setEmail(user.getEmail());
    responseUserDto.setRole(user.getRole().name());
    responseUserDto.setReferralCode(user.getReferralCode());
    responseUserDto.setPoint(pointService.getPointUser(user.getId()));
    return responseUserDto;
  }

}
