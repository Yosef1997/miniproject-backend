package com.tickitz.backend.ticket.repository;

import com.tickitz.backend.ticket.entity.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TicketRepository extends JpaRepository<Ticket, Long> {
  Optional<Ticket> findByName(String name);
}
