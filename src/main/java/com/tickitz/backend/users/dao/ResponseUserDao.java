package com.tickitz.backend.users.dao;


public interface ResponseUserDao {
  Long getId();
  String getUsername();
  String getEmail();
  String getRole();
  String getReferralCode();
  String getPoint();
  Object getReferral();
}