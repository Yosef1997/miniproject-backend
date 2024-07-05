package com.tickitz.backend.referral.service.impl;

import com.tickitz.backend.exceptions.applicationException.ApplicationException;
import com.tickitz.backend.referral.dto.CreateReferralRequestDto;
import com.tickitz.backend.referral.dto.ReferralResponseDto;
import com.tickitz.backend.referral.dto.UpdateReferralRequestDto;
import com.tickitz.backend.referral.entity.Referral;
import com.tickitz.backend.referral.repository.ReferralRepository;
import com.tickitz.backend.referral.service.ReferralService;
import com.tickitz.backend.users.service.UsersService;
import lombok.extern.java.Log;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Log
public class ReferralServiceImpl implements ReferralService {
  private final ReferralRepository referralRepository;
  private final UsersService usersService;

  public ReferralServiceImpl(ReferralRepository referralRepository, UsersService usersService) {
    this.referralRepository = referralRepository;
    this.usersService = usersService;
  }

  @Override
  public List<ReferralResponseDto> getAllReferral() {
    List<Referral> result = referralRepository.findAll();
    return result.stream().map(this::mapToReferralResponseDto).collect(Collectors.toList());
  }

  @Override
  public ReferralResponseDto getReferralUser(Long id) {
    Instant expiredDay = Instant.now().minus(90, ChronoUnit.DAYS);
    Referral referral = referralRepository.findByUserIdAndStatusTrueAndCreatedAtAfter(id, expiredDay).orElseThrow(() -> new ApplicationException("Referral not found"));
    return mapToReferralResponseDto(referral);
  }

  @Override
  public Referral getReferralById(Long id) {
    return referralRepository.findById(id).orElseThrow(()->new ApplicationException("Referral not found"));
  }


  @Override
  public ReferralResponseDto createReferral(CreateReferralRequestDto createReferralRequestDto) {
    Referral savedReferral = referralRepository.save(createReferralRequestDto.toEntity(usersService));
    return mapToReferralResponseDto(savedReferral);
  }

  @Override
  public ReferralResponseDto updateReferral(UpdateReferralRequestDto updateReferralRequestDto) {
    Referral referral = referralRepository.findById(updateReferralRequestDto.getId()).orElseThrow(() -> new ApplicationException("Referral not found"));
    Referral updated = referralRepository.save(updateReferralRequestDto.toEntity(referral));
    return mapToReferralResponseDto(updated);
  }

  @Override
  public String deleteReferral(Long id) {
    referralRepository.findById(id).orElseThrow(() -> new ApplicationException("Referral not found"));
    referralRepository.deleteById(id);
    return "Delete Referral Success";
  }

  public ReferralResponseDto mapToReferralResponseDto(Referral referral) {
    ReferralResponseDto response = new ReferralResponseDto();
    response.setId(referral.getId());
    response.setVoucherName(referral.getVoucherName());
    response.setDiscountPercentage(referral.getDiscountPercentage());
    response.setUserId(referral.getUser().getId());
    response.setStatus(referral.getStatus());
    return response;
  }
}
