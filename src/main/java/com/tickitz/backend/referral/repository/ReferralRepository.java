package com.tickitz.backend.referral.repository;

import com.tickitz.backend.referral.dao.ResponseReferralDao;
import com.tickitz.backend.referral.entity.Referral;
import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Repository
public interface ReferralRepository extends JpaRepository<Referral, Long> {
  @Query(value = "SELECT r.id as id, r.voucherName as voucherName, r.discountPercentage as discountPercentage, " +
          "u.id as userId, r.status as status FROM Referral r JOIN r.user u")
  List<ResponseReferralDao> findAllReferralUser();

  @Query(value = "SELECT r FROM Referral r WHERE r.user.id = :userId AND r.status = TRUE AND r.createdAt >= :expiredDay")
  Optional<Referral> findReferralUser(@Param("userId") Long userId, @Param("expiredDay") Instant expiredDay);
}
