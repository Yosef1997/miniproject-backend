package com.tickitz.backend.users.service.impl;

import com.tickitz.backend.auth.helpers.Claims;
import com.tickitz.backend.exceptions.applicationException.ApplicationException;
import com.tickitz.backend.point.dto.PointRequestDto;
import com.tickitz.backend.point.service.PointService;
import com.tickitz.backend.referral.dto.ReferralRequestDto;
import com.tickitz.backend.referral.dto.ReferralResponseDto;
import com.tickitz.backend.referral.service.ReferralService;
import com.tickitz.backend.users.dao.ResponseUserDao;
import com.tickitz.backend.users.dto.RegisterRequestDto;
import com.tickitz.backend.users.dto.ResponseUserDto;
import com.tickitz.backend.users.dto.UpdateUserRequestDto;
import com.tickitz.backend.users.dto.UpdateUserResponseDto;
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
        ReferralRequestDto referralRequestDto = new ReferralRequestDto();
        referralRequestDto.setId(savedUser.getId());
        referralRequestDto.setReferralCode(requestRegister.getReferral());
        var savedReferral = referralService.createReferral(referralRequestDto);
        referral.setId(savedReferral.getId());
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
    responseUserDto.setAvatar(savedUser.getAvatar());
    responseUserDto.setEmail(savedUser.getEmail());
    responseUserDto.setRole(savedUser.getRole().name());
    responseUserDto.setReferralCode(savedUser.getReferralCode());
    if (savedUser.getRole() == Users.Role.ORGANIZER) {
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
  public List<ResponseUserDao> getAllUser() {
    return usersRepository.findAllUser();
  }

  @Override
  public ResponseUserDto getProfile() {
    var claims = Claims.getClaimsFromJwt();
    var email = (String) claims.get("sub");
    Users user = usersRepository.findByEmail(email).orElseThrow(() -> new ApplicationException("Profile not found"));
    log.info("GetProfile >>>>" + user.toString());
    ResponseUserDto responseUserDto = new ResponseUserDto();
    responseUserDto.setId(user.getId());
    responseUserDto.setUsername(user.getUsername());
    responseUserDto.setAvatar(user.getAvatar());
    responseUserDto.setEmail(user.getEmail());
    responseUserDto.setRole(user.getRole().name());
    responseUserDto.setReferralCode(user.getReferralCode());
    responseUserDto.setPoint(pointService.getPointUser(user.getId()));
    return responseUserDto;
  }

  @Override
  public UpdateUserResponseDto updateUser(UpdateUserRequestDto updateUserRequestDto) {
    Users user = usersRepository.findByEmail(updateUserRequestDto.getEmail()).orElseThrow(() -> new ApplicationException("User not exists"));
    user.setUsername(updateUserRequestDto.getUsername());
    user.setEmail(updateUserRequestDto.getEmail());
    user.setPassword(passwordEncoder.encode(updateUserRequestDto.getPassword()));
    user.setAvatar(updateUserRequestDto.getAvatar());

    Users updatedUser = usersRepository.save(user);

    UpdateUserResponseDto response = new UpdateUserResponseDto();
    response.setId(updatedUser.getId());
    response.setUsername(updatedUser.getUsername());
    response.setEmail(updatedUser.getEmail());
    response.setAvatar(updatedUser.getAvatar());
    response.setRole(updatedUser.getRole().name());
    response.setPoint(pointService.getPointUser(updatedUser.getId()));
    response.setReferral(referralService.getReferralUser(updatedUser.getId()));
    return response;
  }

  @Override
  public ResponseUserDto getDetailUser(String email) {
    ResponseUserDto response = new ResponseUserDto();
    Users user = usersRepository.findByEmail(email).orElseThrow(() -> new ApplicationException("User not exists"));

    response.setId(user.getId());
    response.setUsername(user.getUsername());
    response.setAvatar(user.getAvatar());
    response.setEmail(user.getEmail());
    response.setRole(user.getRole().name());
    response.setReferralCode(user.getReferralCode());
    response.setPoint(pointService.getPointUser(user.getId()));
    response.setReferralVoucher(referralService.getReferralUser(user.getId()));
    return response;
  }

  @Override
  public Users getDetailUserId(Long id) {
    return usersRepository.findById(id).orElseThrow(() -> new ApplicationException("User not exists"));
  }
}
