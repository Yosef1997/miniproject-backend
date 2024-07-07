package com.tickitz.backend.category.controller;

import com.tickitz.backend.category.dto.CategoryResponseDto;
import com.tickitz.backend.category.dto.CreateCategoryRequestDto;
import com.tickitz.backend.category.dto.UpdateCategoryRequestDto;
import com.tickitz.backend.category.service.CategoryService;
import com.tickitz.backend.response.Response;
import lombok.extern.java.Log;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/category")
@Validated
@Log
public class CategoryController {
  private final CategoryService categoryService;

  public CategoryController(CategoryService categoryService) {
    this.categoryService = categoryService;
  }

  @GetMapping
  public ResponseEntity<Response<List<CategoryResponseDto>>> getAllCategories() {
    return Response.successResponse("All Categories fetched", categoryService.getAllCategories());
  }

  @GetMapping("/{id}")
  public ResponseEntity<Response<Object>> getDetailCategory(@PathVariable Long id) {
    return Response.successResponse(categoryService.getDetailCategory(id));
  }

  @PostMapping
  public ResponseEntity<Response<CategoryResponseDto>> createCategory(@RequestBody CreateCategoryRequestDto createCategoryRequestDto) {
    return Response.successResponse("Create Category Success", categoryService.createCategory(createCategoryRequestDto));
  }

  @PutMapping
  public ResponseEntity<Response<CategoryResponseDto>> updateCategory(@RequestBody UpdateCategoryRequestDto updateCategoryRequestDto) {
    return Response.successResponse("Update Category Success", categoryService.updateCategory(updateCategoryRequestDto));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Response<Object>> deleteCategory(@PathVariable Long id) {
    return Response.successResponse(categoryService.deleteCategory(id));
  }
}
