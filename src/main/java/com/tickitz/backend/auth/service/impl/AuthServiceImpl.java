package com.tickitz.backend.auth.service.impl;

import com.tickitz.backend.auth.dto.ResetPasswordRequestDto;
import com.tickitz.backend.auth.helpers.Claims;
import com.tickitz.backend.auth.repository.AuthRedisRepository;
import com.tickitz.backend.auth.service.AuthService;
import com.tickitz.backend.exceptions.applicationException.ApplicationException;
import com.tickitz.backend.users.entity.Users;
import com.tickitz.backend.users.repository.UsersRepository;
import lombok.extern.java.Log;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Log
public class AuthServiceImpl implements AuthService {
  private final JwtEncoder jwtEncoder;
  private final PasswordEncoder passwordEncoder;
  private final UsersRepository usersRepository;
  private final AuthRedisRepository authRedisRepository;

  public AuthServiceImpl(JwtEncoder jwtEncoder, PasswordEncoder passwordEncoder, UsersRepository usersRepository, AuthRedisRepository authRedisRepository) {
    this.jwtEncoder = jwtEncoder;
    this.passwordEncoder = passwordEncoder;
    this.usersRepository = usersRepository;
    this.authRedisRepository = authRedisRepository;
  }

  @Override
  public String generateToken(Authentication authentication) {
    Instant now = Instant.now();

    String scope = authentication.getAuthorities()
            .stream()
            .map(GrantedAuthority::getAuthority)
            .collect(Collectors.joining(" "));

        var existingKey = authRedisRepository.getJwtKey(authentication.getName());
    if (existingKey !=null) {
      log.info("Token already exists for user: " + authentication.getName()) ;
      return existingKey;
    }

    JwtClaimsSet claims = JwtClaimsSet.builder()
            .issuer("self")
            .issuedAt(now)
            .expiresAt(now.plus(10, ChronoUnit.HOURS))
            .subject(authentication.getName())
            .claim("scope", scope)
            .claim("userId", usersRepository.findByEmail(authentication.getName()).get().getId())
            .build();

    var jwt = jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();

    if (authRedisRepository.isKeyBlacklisted(jwt)) {
      throw new ApplicationException("Token has been blacklisted");
    }

    authRedisRepository.saveJwtKey(authentication.getName(), jwt);
    return jwt;
  }

  @Override
  public String resetPassword(ResetPasswordRequestDto resetPasswordRequestDto) {
    Optional<Users> exists = usersRepository.findByEmail(resetPasswordRequestDto.getEmail());
    if (exists.isPresent()) {
      Users user = exists.get();
      String encodedNewPassword = passwordEncoder.encode(resetPasswordRequestDto.getPassword());
      user.setPassword(encodedNewPassword);
      usersRepository.save(user);
      return "Reset Password success";
    }
    throw new ApplicationException("User not found");
  }

  @Override
  public String logout() {
    var claims = Claims.getClaimsFromJwt();
    var email = (String) claims.get("sub");
    String jwt = authRedisRepository.getJwtKey(email);
    authRedisRepository.blackListJwt(email, jwt);
    authRedisRepository.deleteJwtKey(email);
    return "Logout success";
  }


}
