package com.tickitz.backend.referral.service;

import com.tickitz.backend.referral.dto.CreateReferralRequestDto;
import com.tickitz.backend.referral.dto.ReferralResponseDto;
import com.tickitz.backend.referral.dto.UpdateReferralRequestDto;
import com.tickitz.backend.referral.entity.Referral;

import java.util.List;

public interface ReferralService {
  List<ReferralResponseDto> getAllReferral();
  ReferralResponseDto getReferralUser(Long id);
  Referral getReferralById(Long id);
  ReferralResponseDto createReferral(CreateReferralRequestDto createReferralRequestDto);
  ReferralResponseDto updateReferral(UpdateReferralRequestDto updateReferralRequestDto);
  String deleteReferral(Long id);

}
