package com.tickitz.backend.ticket.repository;

import com.tickitz.backend.ticket.dao.TicketDao;
import com.tickitz.backend.ticket.entity.Ticket;
import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface TicketRepository extends JpaRepository<Ticket, Long> {
  @Query(value = "SELECT t.id as id, t.name as name, t.seats as seats, t.price as price, e.id as eventId " +
                 "FROM Ticket t LEFT JOIN t.event e")
  List<TicketDao> findAllTicket();

  @Query(value = "SELECT t.id as id, t.name as name, t.seats as seats, t.price as price, e.id as eventId " +
          "FROM Ticket t LEFT JOIN t.event e " +
          "WHERE e.id = :eventId " +
          "GROUP BY t.id, e.id")
  List<TicketDao> findAllTicketByEventId(@Param("eventId") Long eventId);
}
