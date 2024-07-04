package com.tickitz.backend.promotion.repository;

import com.tickitz.backend.promotion.dao.PromotionDao;
import com.tickitz.backend.promotion.entity.Promotion;
import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface PromotionRepository extends JpaRepository<Promotion, Long> {
  @Query(value = "SELECT p.id as id, p.name as name, p.type as type, p.usageLimit as usageLimit, p.discount as discount, " +
          "p.expiredDate as expiredDate, e.id as eventId FROM Promotion p LEFT JOIN p.event e")
  List<PromotionDao> findAllPromotion();

  @Query(value = "SELECT p.id as id, p.name as name, p.type as type, p.usageLimit as usageLimit, p.discount as discount, " +
          "p.expiredDate as expiredDate, e.id as eventId FROM Promotion p LEFT JOIN p.event e " +
          "WHERE e.id = :eventId " +
          "GROUP BY p.id, e.id")
  List<PromotionDao> findAllPromotionByEventId(@Param("eventId") Long eventId);
}
