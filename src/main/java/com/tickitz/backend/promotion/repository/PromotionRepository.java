package com.tickitz.backend.promotion.repository;

import com.tickitz.backend.promotion.entity.Promotion;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface PromotionRepository extends JpaRepository<Promotion, Long> {
  Optional<Promotion> findByName(String name);
}
