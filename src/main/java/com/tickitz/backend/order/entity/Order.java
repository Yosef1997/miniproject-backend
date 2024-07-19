package com.tickitz.backend.order.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.tickitz.backend.event.entity.Event;
import com.tickitz.backend.promotion.entity.Promotion;
import com.tickitz.backend.ticketOrder.entity.TicketOrder;
import com.tickitz.backend.users.entity.Users;
import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.Fetch;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "order", schema = "miniproj")
@NoArgsConstructor
@Getter
@Setter
public class Order {
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE)
  private Long id;

  @DecimalMin(value = "0.0", message = "Total price must be non-negative")
  @Column(name = "total_price", nullable = false)
  private BigDecimal totalPrice;

  @Min(value = 1, message = "Minimum total ticket is one")
  @Column(name = "total_ticket", nullable = false)
  private Integer totalTicket;

  @Min(value = 0, message = "Total price must be non-negative")
  @Column(name = "used_point", nullable = false)
  private Long usedPoint;

  @NotNull(message = "User id is required")
  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
  private Users user;

  @NotNull(message = "Organizer id is required")
  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "organizer_id", referencedColumnName = "id", nullable = false)
  private Users organizer;

  @NotNull(message = "Event id is required")
  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "event_id", referencedColumnName = "id", nullable = false)
  private Event event;

  @JsonIgnore
  @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
  @Column(nullable = false)
  private List<TicketOrder> ticketOrders = new ArrayList<>();

  @ManyToMany(fetch = FetchType.EAGER)
  @JoinTable(
          name = "order_promotion",
          joinColumns = @JoinColumn(name = "order_id"),
          inverseJoinColumns = @JoinColumn(name = "promotion_id")
  )
  private List<Promotion> promotions;

  @NotNull
  @ColumnDefault("CURRENT_TIMESTAMP")
  @Column(name = "created_at", nullable = false)
  private Instant createdAt;

  @NotNull
  @ColumnDefault("CURRENT_TIMESTAMP")
  @Column(name = "updated_at", nullable = false)
  private Instant updatedAt;

  @Column(name = "deleted_at")
  private Instant deletedAt;

  @PrePersist
  public void prePersist() {
    this.createdAt = Instant.now();
    this.updatedAt = Instant.now();
  }

  @PreUpdate
  public void preUpdate() {
    this.updatedAt = Instant.now();
  }

  @PreRemove
  public void preRemove() {
    this.deletedAt = Instant.now();
  }
}
