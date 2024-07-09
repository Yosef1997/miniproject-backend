package com.tickitz.backend.promotion.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.tickitz.backend.event.entity.Event;
import com.tickitz.backend.order.entity.Order;
import com.tickitz.backend.types.PromotionTypeEnum;
import com.tickitz.backend.users.entity.Users;
import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.time.Instant;
import java.util.List;

@Entity
@Table(name = "promotion", schema = "miniproj")
@NoArgsConstructor
@Getter
@Setter
public class Promotion {
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE)
  private Long id;

  @NotBlank
  @Column(nullable = false)
  private String name;

  @Column(nullable = false )
  @Enumerated(EnumType.STRING)
  private PromotionTypeEnum type;

  @Min(value = 1, message = "Minimum usage limit is 1")
  @Column(name = "usage_limit", nullable = false)
  private Integer usageLimit;

  @DecimalMin(value = "0.1", message = "Minimum discount is 0.1")
  @Column(nullable = false)
  private Double discount;

  @Column(name = "expired_date", nullable = false)
  private Instant expiredDate;

  @JsonIgnore
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "event_id", nullable = false)
  private Event event;

  @JsonIgnore
  @ManyToMany(mappedBy = "promotions")
  private List<Order> orders;

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
