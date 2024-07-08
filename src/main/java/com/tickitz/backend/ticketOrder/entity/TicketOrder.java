package com.tickitz.backend.ticketOrder.entity;

import com.tickitz.backend.order.entity.Order;
import com.tickitz.backend.ticket.entity.Ticket;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "ticket_order", schema = "miniproj")
@NoArgsConstructor
@Getter
@Setter
public class TicketOrder {
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE)
  private Long id;

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "order_id", nullable = false)
  private Order order;

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "ticket_id", nullable = false)
  private Ticket ticket;

  @Min(value = 1)
  @Column(nullable = false)
  private Long quantity;
}
