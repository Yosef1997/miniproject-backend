package com.tickitz.backend.referral.repository;

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
Optional<Referral> findByUserIdAndStatusTrueAndCreatedAtAfter(Long userId, Instant expiredDay);
}
