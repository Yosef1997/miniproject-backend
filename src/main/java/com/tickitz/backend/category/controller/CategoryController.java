package com.tickitz.backend.category.controller;

import com.tickitz.backend.category.entity.Category;
import com.tickitz.backend.category.service.CategoryService;
import com.tickitz.backend.response.Response;
import lombok.extern.java.Log;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/category")
@Log
public class CategoryController {
  private final CategoryService categoryService;

  public CategoryController(CategoryService categoryService) {
    this.categoryService = categoryService;
  }

  @GetMapping("/")
  public ResponseEntity<Response<List<Category>>> getAllCategories() {
    return Response.successResponse("All Categories fetched", categoryService.getAllCategories());
  }

  @PostMapping("/")
  public ResponseEntity<Response<Category>> createCategory(@Validated @RequestBody Category category) {
    return Response.successResponse("Create Category Success", categoryService.createCategory(category));
  }
}
