package com.tickitz.backend.users.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.tickitz.backend.event.entity.Event;
import com.tickitz.backend.order.entity.Order;
import com.tickitz.backend.point.entity.Point;
import com.tickitz.backend.referral.entity.Referral;
import com.tickitz.backend.review.entity.Review;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.time.Instant;
import java.util.Set;

@Entity
@Table(name = "users", schema = "miniproj")
@NoArgsConstructor
@Getter
@Setter
public class Users {
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE)
  @Column(nullable = false)
  private Long id;

  @Size(max = 255)
  @Column(length = 150)
  private String username;

  @Size(max = 150)
  @NotNull
  @Column(nullable = false, length = 150, unique = true)
  private String email;

  @JsonIgnore
  @Size(max = 100)
  @NotNull
  @Column(nullable = false, length = 100)
  private String password;

  @Size(max = 255)
  @Column(length = 255)
  private String avatar;

  @Column(nullable = false )
  @Enumerated(EnumType.STRING)
  private Role role;

  @Column(name="referral_code")
  private String referralCode;

  @Size(max = 12)
  @Column(length = 12)
  private String phone;

  @JsonIgnore
  @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
  private Set<Point> point;

  @JsonIgnore
  @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
  private Referral referral;

  @JsonIgnore
  @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
  private Set<Event> event;

  @JsonIgnore
  @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
  private Set<Review> review;

  @JsonIgnore
  @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
  private Set<Order> order;

  @JsonIgnore
  @OneToMany(mappedBy = "organizer", cascade = CascadeType.ALL, orphanRemoval = true)
  private Set<Order> organizer;

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

  public enum Role {
    CUSTOMER,
    ORGANIZER
  }
}
