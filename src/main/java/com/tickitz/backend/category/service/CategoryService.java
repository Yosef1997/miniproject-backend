package com.tickitz.backend.category.service;

import com.tickitz.backend.category.entity.Category;

import java.util.List;

public interface CategoryService {
  List<Category> getAllCategories();

  Category createCategory(Category category);
}
