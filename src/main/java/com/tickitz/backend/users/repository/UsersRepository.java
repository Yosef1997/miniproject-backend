package com.tickitz.backend.users.repository;

import com.tickitz.backend.users.dao.ResponseUserDao;
import com.tickitz.backend.users.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UsersRepository extends JpaRepository<Users, Long> {
  @Query(value = "SELECT u.id as id, u.username as username, u.email as email, u.role as role, " +
          "u.referralCode as referralCode, COALESCE(SUM(p.point), 0) as point,  u.referral as referral FROM Users u JOIN u.point p JOIN u.referral r " +
          " GROUP BY u.id, u.referral")
  List<ResponseUserDao> findAllUser();

  Optional<Users> findByEmail(String email);

  Optional<Users> findByReferralCode(String referralCode);
}
