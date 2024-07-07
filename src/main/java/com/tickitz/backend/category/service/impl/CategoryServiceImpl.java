package com.tickitz.backend.category.service.impl;

import com.tickitz.backend.category.dto.CategoryResponseDto;
import com.tickitz.backend.category.dto.CreateCategoryRequestDto;
import com.tickitz.backend.category.dto.UpdateCategoryRequestDto;
import com.tickitz.backend.category.entity.Category;
import com.tickitz.backend.category.repository.CategoryRepository;
import com.tickitz.backend.category.service.CategoryService;
import com.tickitz.backend.exceptions.applicationException.ApplicationException;
import lombok.extern.java.Log;
import org.springframework.context.ApplicationContextException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Log
public class CategoryServiceImpl implements CategoryService {
  private final CategoryRepository categoryRepository;

  public CategoryServiceImpl(CategoryRepository categoryRepository) {
    this.categoryRepository = categoryRepository;
  }


  @Override
  public List<CategoryResponseDto> getAllCategories() {
    List<Category> result = categoryRepository.findAll();
    return result.stream().map(this::mapToCategoryResponseDto).collect(Collectors.toList());
  }

  @Override
  public CategoryResponseDto getDetailCategory(Long id) {
    Category detail = categoryRepository.findById(id).orElse(null);
    if (detail == null) return null;
    return mapToCategoryResponseDto(detail);
  }

  @Override
  public CategoryResponseDto createCategory(CreateCategoryRequestDto createCategoryRequestDto) {
    Optional<Category> exists = categoryRepository.findAllByCategoryName(createCategoryRequestDto.getCategoryName());
    if (exists.isPresent()) {
      throw new ApplicationContextException("Category name: " + createCategoryRequestDto.getCategoryName() + " already exists");
    }
    Category newCategory = categoryRepository.save(createCategoryRequestDto.toEntity());
    return mapToCategoryResponseDto(newCategory);
  }

  @Override
  public CategoryResponseDto updateCategory(UpdateCategoryRequestDto updateCategoryRequestDto) {
    Category result = categoryRepository.findById(updateCategoryRequestDto.getId()).orElseThrow(() -> new ApplicationException("Category with id: " + updateCategoryRequestDto.getId() + " not exists"));
    Category updated = categoryRepository.save(updateCategoryRequestDto.toEntity(result));
    return mapToCategoryResponseDto(updated);
  }

  @Override
  public String deleteCategory(Long id) {
    getDetailCategory(id);
    categoryRepository.deleteById(id);
    return "Delete Category success";
  }

  public CategoryResponseDto mapToCategoryResponseDto(Category category) {
    CategoryResponseDto response = new CategoryResponseDto();
    response.setId(category.getId());
    response.setCategoryName(category.getCategoryName());
    return response;
  }
}
