package com.tickitz.backend.users.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.time.Instant;

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