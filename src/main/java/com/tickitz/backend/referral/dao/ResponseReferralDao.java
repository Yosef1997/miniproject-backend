package com.tickitz.backend.referral.dao;

public interface ResponseReferralDao {
  Long getId();
  String getVoucherName();
  Integer getDiscountPercentage();
  Long getUserId();
  Boolean getStatus();
}
