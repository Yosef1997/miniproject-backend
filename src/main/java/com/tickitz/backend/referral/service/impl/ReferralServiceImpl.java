package com.tickitz.backend.referral.service.impl;

import com.tickitz.backend.exceptions.applicationException.ApplicationException;
import com.tickitz.backend.referral.dao.ResponseReferralDao;
import com.tickitz.backend.referral.dto.ReferralRequestDto;
import com.tickitz.backend.referral.dto.ReferralResponseDto;
import com.tickitz.backend.referral.dto.UpdateRequestDto;
import com.tickitz.backend.referral.entity.Referral;
import com.tickitz.backend.referral.repository.ReferralRepository;
import com.tickitz.backend.referral.service.ReferralService;
import com.tickitz.backend.users.entity.Users;
import com.tickitz.backend.users.repository.UsersRepository;
import lombok.extern.java.Log;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;

@Service
@Log
public class ReferralServiceImpl implements ReferralService {
  private final ReferralRepository referralRepository;
  private final UsersRepository usersRepository;

  public ReferralServiceImpl(ReferralRepository referralRepository, UsersRepository usersRepository) {
    this.referralRepository = referralRepository;
    this.usersRepository = usersRepository;
  }

  @Override
  public List<ResponseReferralDao> getAllReferral() {
    return referralRepository.findAllReferralUser();
  }

  @Override
  public ReferralResponseDto createReferral(ReferralRequestDto referralRequestDto) {
    Users user = usersRepository.findById(referralRequestDto.getId()).orElseThrow(()-> new ApplicationException("User not exists"));

    Referral referral =new Referral();
    referral.setVoucherName("Referral for " + referralRequestDto.getReferralCode());
    referral.setDiscountPercentage(10);
    referral.setUser(user);
    referral.setStatus(true);

    Referral savedReferral = referralRepository.save(referral);

    ReferralResponseDto responseDto = new ReferralResponseDto();
    responseDto.setId(savedReferral.getId());
    responseDto.setVoucherName(savedReferral.getVoucherName());
    responseDto.setDiscountPercentage(savedReferral.getDiscountPercentage());
    responseDto.setUserId(savedReferral.getUser().getId());
    responseDto.setStatus(savedReferral.getStatus());

    return responseDto;
  }

  @Override
  public ReferralResponseDto getReferralUser(Long id) {
    Instant expiredDay = Instant.now().minus(90, ChronoUnit.DAYS);
    Optional<Referral> referral = referralRepository.findReferralUser(id, expiredDay);
    if (referral.isPresent()) {
      ReferralResponseDto responseDto = new ReferralResponseDto();
      responseDto.setId(referral.get().getId());
      responseDto.setVoucherName(referral.get().getVoucherName());
      responseDto.setDiscountPercentage(referral.get().getDiscountPercentage());
      responseDto.setUserId(referral.get().getUser().getId());
      responseDto.setStatus(referral.get().getStatus());
      return responseDto;
    }
    return null;
  }

  @Override
  public String updateReferral(UpdateRequestDto requestDto) {
    Optional<Referral> referral = referralRepository.findById(requestDto.getId());
    if (referral.isPresent()) {
      Referral newReferral = referral.get();
      newReferral.setId(requestDto.getId());
      newReferral.setVoucherName(requestDto.getVoucherName());
      newReferral.setDiscountPercentage(requestDto.getDiscountPercentage());
      newReferral.setUser(referral.get().getUser());
      newReferral.setStatus(requestDto.getStatus());

      referralRepository.save(newReferral);
      return "Update Referral Success";
    }
    return "Update Referral Failed";
  }


}
