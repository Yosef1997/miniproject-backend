package com.tickitz.backend.category.service;

import com.tickitz.backend.category.dto.CategoryResponseDto;
import com.tickitz.backend.category.dto.CreateCategoryRequestDto;
import com.tickitz.backend.category.dto.UpdateCategoryRequestDto;
import com.tickitz.backend.category.entity.Category;

import java.util.List;

public interface CategoryService {
  List<CategoryResponseDto> getAllCategories();

  CategoryResponseDto getDetailCategory(Long id);

  CategoryResponseDto createCategory(CreateCategoryRequestDto createCategoryRequestDto);

  CategoryResponseDto updateCategory(UpdateCategoryRequestDto updateCategoryRequestDto);

  String deleteCategory(Long id);
}
