package com.tickitz.backend.referral.dto;

import com.tickitz.backend.referral.entity.Referral;
import com.tickitz.backend.users.service.UsersService;
import lombok.Data;

import java.util.UUID;

@Data
public class CreateReferralRequestDto {
  private Integer discountPercentage = 10;
  private Long userId;
  private Boolean status = true;

  public Referral toEntity(UsersService usersService) {
    Referral newReferral = new Referral();
    newReferral.setDiscountPercentage(discountPercentage);
    newReferral.setUser(usersService.getDetailUserId(userId));
    newReferral.setStatus(status);
    return newReferral;
  }
}