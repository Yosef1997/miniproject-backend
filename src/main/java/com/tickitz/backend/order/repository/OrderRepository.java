package com.tickitz.backend.order.repository;

import com.tickitz.backend.order.entity.Order;
import com.tickitz.backend.sales.dao.*;
import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
  @Query("SELECT o.event.id as eventId, o.event.eventName as eventName, SUM(o.totalPrice) as totalSales, DATE_TRUNC(:interval, o.createdAt) as date " +
          "FROM Order o " +
          "WHERE o.event.id = :eventId AND o.createdAt >= :startDate AND o.createdAt <= :endDate " +
          "GROUP BY o.event.id, o.event.eventName, date")
  List<SalesDataDao> findSalesData(@Param("interval") String interval, @Param("eventId") Long eventId, @Param("startDate") Instant startDate, @Param("endDate") Instant endDate);

  @Query("SELECT o.organizer.id as organizerId, SUM(o.totalPrice) as totalSales, DATE_TRUNC(:interval, o.createdAt) as date " +
          "FROM Order o " +
          "WHERE o.organizer.id = :organizerId AND o.createdAt >= :startDate AND o.createdAt <= :endDate " +
          "GROUP BY o.organizer.id, date")
  List<SalesOrganizerDao> findSalesOrganizer(@Param("interval") String interval, @Param("organizerId") Long organizerId, @Param("startDate") Instant startDate, @Param("endDate") Instant endDate);

  @Query("SELECT o.event.id as eventId, o.event.eventName as eventName, SUM(o.totalTicket) as totalTicket, DATE_TRUNC(:interval, o.createdAt) as date " +
          "FROM Order o " +
          "WHERE o.event.id = :eventId AND o.createdAt >= :startDate AND o.createdAt <= :endDate " +
          "GROUP BY o.event.id, o.event.eventName, date")
  List<SalesTicketDao> findSalesTicket(@Param("interval") String interval, @Param("eventId") Long eventId, @Param("startDate") Instant startDate, @Param("endDate") Instant endDate);

  @Query("SELECT o.organizer.id as organizerId, SUM(o.totalTicket) as totalTicket, DATE_TRUNC(:interval, o.createdAt) as date " +
          "FROM Order o " +
          "WHERE o.organizer.id = :organizerId AND o.createdAt >= :startDate AND o.createdAt <= :endDate " +
          "GROUP BY o.organizer.id, date")
  List<SalesTicketOrganizerDao> findSalesTicketOrganizer(@Param("interval") String interval, @Param("organizerId") Long organizerId, @Param("startDate") Instant startDate, @Param("endDate") Instant endDate);

  @Query("SELECT o.event.id as eventId, SUM(o.totalPrice) as totalSales , SUM(o.totalTicket) as totalTicket " +
          "FROM Order o " +
          "WHERE o.event.id = :eventId " +
          "GROUP BY eventId")
  SalesTotalSalesTotalTicketEventDao findTotalSalesTotalTicketEvent(@Param("eventId") Long eventId);

  @Query("SELECT o.organizer.id as organizerId, SUM(o.totalPrice) as totalSales , SUM(o.totalTicket) as totalTicket " +
          "FROM Order o " +
          "WHERE o.organizer.id = :organizerId " +
          "GROUP BY organizerId")
  SalesTotalSalesTotalTicketOrganizerDao findTotalSalesTotalTicketOrganizer(@Param("organizerId") Long organizerId);
}
