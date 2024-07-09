package com.tickitz.backend.order.repository;

import com.tickitz.backend.order.dao.SalesDao;
import com.tickitz.backend.order.entity.Order;
import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.Instant;
import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
  @Query("SELECT o.id as id, e.id as eventId, e.eventName as eventName, SUM(o.totalPrice) as totalPrice, o.createdAt as date " +
          "FROM Order o JOIN o.event e " + "WHERE e.id = :eventId AND o.createdAt BETWEEN :startDate AND :endDate " +
          "GROUP BY o.id, e.id, o.createdAt " + "ORDER BY o.createdAt")
  List<SalesDao> findSalesData(@Param("eventId") Long eventId, @Param("startDate") Instant startDate,
                               @Param("endDate") Instant endDate);
}
