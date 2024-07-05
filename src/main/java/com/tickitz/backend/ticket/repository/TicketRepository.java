package com.tickitz.backend.ticket.repository;

import com.tickitz.backend.ticket.entity.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TicketRepository extends JpaRepository<Ticket, Long> {
  List<Ticket> findAllByEventId(Long eventId);
}
