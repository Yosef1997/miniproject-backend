package com.tickitz.backend.event.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.tickitz.backend.review.entity.Review;
import com.tickitz.backend.ticket.entity.Ticket;
import com.tickitz.backend.promotion.entity.Promotion;
import com.tickitz.backend.users.entity.Users;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "event", schema = "miniproj")
@NoArgsConstructor
@Getter
@Setter
public class Event {
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE)
  private Long id;

  @NotNull
  @NotBlank(message = "Event name is required")
  @Column(name = "event_name", nullable = false)
  private String eventName;

  @NotNull
  @NotBlank(message = "Event image is required")
  @Column(name = "event_image", nullable = false)
  private String eventImage;

  @NotNull
  @NotBlank(message = "Category is required")
  @Column(nullable = false)
  private String category;

  @NotNull
  @NotBlank(message = "Location is required")
  @Column(nullable = false)
  private String location;

  @NotNull
  @NotBlank(message = "Venue is required")
  @Column(nullable = false)
  private String venue;

  @NotNull
  @NotBlank(message = "Description is required")
  @Column(nullable = false)
  private String description;

  @NotNull
  @Column(nullable = false)
  private Instant date;

  @NotNull
  @Column(name = "start_time", nullable = false)
  private Instant startTime;

  @NotNull
  @Column(name = "end_time", nullable = false)
  private Instant endTime;

  @JsonIgnore
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "user_id", nullable = false)
  private Users user;

  @JsonIgnore
  @OneToMany(mappedBy = "event", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<Ticket> tickets;

  @JsonIgnore
  @OneToMany(mappedBy = "event", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<Promotion> promotions;

  @JsonIgnore
  @OneToMany(mappedBy = "event", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<Review> reviews;

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
