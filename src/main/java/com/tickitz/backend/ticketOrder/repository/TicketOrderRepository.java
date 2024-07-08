package com.tickitz.backend.ticketOrder.repository;

import com.tickitz.backend.ticketOrder.entity.TicketOrder;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TicketOrderRepository extends JpaRepository<TicketOrder, Long> {
  List<TicketOrder> findAllByOrderId(Long orderId);
}
