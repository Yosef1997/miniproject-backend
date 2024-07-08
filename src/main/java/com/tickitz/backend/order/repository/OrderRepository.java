package com.tickitz.backend.order.repository;

import com.tickitz.backend.order.entity.Order;
import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
  @Query(value = "SELECT p.id as promoId FROM Order o LEFT JOIN o.promotions p " +
          "WHERE o.id = :orderId")
  List<Long> findAllPromoIdByOrderId(@Param("orderId") Long orderId);
}
