package com.tickitz.backend.category.repository;

import com.tickitz.backend.category.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Long> {
  Optional<Category> findAllByCategoryName(String categoryName);
}
