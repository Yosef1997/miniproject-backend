package com.tickitz.backend.category.service;

import com.tickitz.backend.category.entity.Category;
import com.tickitz.backend.category.repository.CategoryRepository;
import lombok.extern.java.Log;
import org.springframework.context.ApplicationContextException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Log
public class CategoryServiceImpl implements CategoryService {
  private final CategoryRepository categoryRepository;

  public CategoryServiceImpl(CategoryRepository categoryRepository) {
    this.categoryRepository = categoryRepository;
  }


  @Override
  public List<Category> getAllCategories() {
    return categoryRepository.findAll();
  }

  @Override
  public Category createCategory(Category category) {
    Optional<Category> exists = categoryRepository.findAllByCategoryName(category.getCategoryName());
    if (exists.isPresent()) {
      throw new ApplicationContextException("Category name already exists");
    }
    return categoryRepository.save(category);
  }
}
