package com.tickitz.backend.ticket.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.tickitz.backend.event.entity.Event;
import jakarta.persistence.*;
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
@Table(name = "ticket", schema = "miniproj")
@NoArgsConstructor
@Getter
@Setter
public class Ticket {
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE)
  private Long id;

  @NotBlank
  @Column(nullable = false)
  private String name;

  @Min(value = 1, message = "Minimum seats is one")
  @Column(nullable = false)
  private Integer seats;

  @Min(value = 1, message = "Minimum price is one")
  @Column(nullable = false)
  private Long price;

  @Column(name = "event_id", nullable = false)
  private Long eventId;

  @JsonIgnore
  @ManyToMany(mappedBy = "tickets")
  private List<Event> events;

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
