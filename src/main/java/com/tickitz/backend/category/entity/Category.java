package com.tickitz.backend.category.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.time.Instant;
import java.time.LocalDateTime;

@Entity
@Table(name = "category", schema = "miniproj")
@NoArgsConstructor
@Getter
@Setter
public class Category {
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE)
  private Long id;

  @NotNull
  @NotBlank(message = "Category name is required")
  @Column(name = "category_name", nullable = false)
  private String categoryName;


  @Column(name = "created_at", nullable = false, updatable = false)
  @ColumnDefault("CURRENT_TIMESTAMP")
  private Instant createdAt;

  @Column(name = "updated_at", nullable = false, insertable = false)
  @ColumnDefault("CURRENT_TIMESTAMP")
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
