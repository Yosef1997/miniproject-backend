package com.tickitz.backend.users.service.impl;

import com.tickitz.backend.auth.helpers.Claims;
import com.tickitz.backend.exceptions.applicationException.ApplicationException;
import com.tickitz.backend.point.dto.CreatePointRequestDto;
import com.tickitz.backend.point.service.PointService;
import com.tickitz.backend.referral.dto.CreateReferralRequestDto;
import com.tickitz.backend.referral.dto.ReferralResponseDto;
import com.tickitz.backend.referral.service.ReferralService;
import com.tickitz.backend.users.dto.RegisterRequestDto;
import com.tickitz.backend.users.dto.ResponseUserDto;
import com.tickitz.backend.users.dto.UpdateUserRequestDto;
import com.tickitz.backend.users.entity.Users;
import com.tickitz.backend.users.repository.UsersRepository;
import com.tickitz.backend.users.service.UsersService;
import lombok.extern.java.Log;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Log
public class UsersServiceImpl implements UsersService {
  private final UsersRepository usersRepository;
  private final PasswordEncoder passwordEncoder;
  private final PointService pointService;
  private final ReferralService referralService;

  public UsersServiceImpl(UsersRepository usersRepository, PasswordEncoder passwordEncoder, PointService pointService,
                          @Lazy ReferralService referralService) {
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
    newUser.setPassword(passwordEncoder.encode(requestRegister.getPassword()));
    if (requestRegister.getRole() == Users.Role.ORGANIZER) {
      newUser.setReferralCode(null);
    } else {
      newUser.setReferralCode(UUID.randomUUID().toString().replace("-", "").substring(0, 10));
    }
    Users savedUser = usersRepository.save(newUser);

    if (requestRegister.getRole() == Users.Role.CUSTOMER && !Objects.equals(requestRegister.getReferral(), "")) {
      Optional<Users> referrer = usersRepository.findByReferralCode(requestRegister.getReferral());
      if (referrer.isPresent()) {
        CreateReferralRequestDto newReferral = new CreateReferralRequestDto();
        newReferral.setUserId(savedUser.getId());
        referralService.createReferral(newReferral);

        CreatePointRequestDto newPoint = new CreatePointRequestDto();
        newPoint.setUserId(referrer.get().getId());
        newPoint.setPoint(10000L);
        pointService.createPoints(newPoint);
      }
    }
    return mapToResponseUserDto(savedUser);
  }

  @Override
  public List<ResponseUserDto> getAllUser() {
    List<Users> result = usersRepository.findAll();
    return result.stream().map(this::mapToResponseUserDto).collect(Collectors.toList());
  }

  @Override
  public ResponseUserDto getProfile() {
    var claims = Claims.getClaimsFromJwt();
    var email = (String) claims.get("sub");
    Users user = usersRepository.findByEmail(email).orElseThrow(() -> new ApplicationException("Profile not found"));
    return mapToResponseUserDto(user);
  }

  @Override
  public ResponseUserDto updateUser(UpdateUserRequestDto updateUserRequestDto) {
    Users user = usersRepository.findByEmail(updateUserRequestDto.getEmail()).orElseThrow(() -> new ApplicationException("User not exists"));
    Users edited = updateUserRequestDto.toEntity(user);
    edited.setPassword(passwordEncoder.encode(updateUserRequestDto.getPassword()));
    Users updatedUser = usersRepository.save(edited);

    return mapToResponseUserDto(updatedUser);
  }

  @Override
  public ResponseUserDto getDetailUser(String email) {
    Users user = usersRepository.findByEmail(email).orElseThrow(() -> new ApplicationException("User not exists"));
    return mapToResponseUserDto(user);
  }

  @Override
  public Users getDetailUserId(Long id) {
    return usersRepository.findById(id).orElseThrow(() -> new ApplicationException("User not exists"));
  }

  public ResponseUserDto mapToResponseUserDto(Users user) {
    ResponseUserDto response = new ResponseUserDto();
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
}
