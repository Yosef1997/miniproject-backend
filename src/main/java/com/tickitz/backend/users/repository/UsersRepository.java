package com.tickitz.backend.users.repository;

import com.tickitz.backend.users.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UsersRepository extends JpaRepository<Users, Long> {

  Optional<Users> findByEmail(String email);
  Optional<Users> findByReferralCode(String referralCode);
}
