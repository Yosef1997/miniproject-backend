package com.tickitz.backend.referral.service;

import com.tickitz.backend.referral.dao.ResponseReferralDao;
import com.tickitz.backend.referral.dto.ReferralRequestDto;
import com.tickitz.backend.referral.dto.ReferralResponseDto;
import com.tickitz.backend.referral.dto.UpdateRequestDto;

import java.util.List;

public interface ReferralService {
List<ResponseReferralDao> getAllReferral();
ReferralResponseDto createReferral(ReferralRequestDto referralRequestDto);
ReferralResponseDto getReferralUser(Long id);
String updateReferral(UpdateRequestDto requestDto);
}
