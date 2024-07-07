package com.tickitz.backend.category.dto;

import com.tickitz.backend.category.entity.Category;
import lombok.Data;

@Data
public class UpdateCategoryRequestDto {
  private Long id;
  private String categoryName;

  public Category toEntity(Category category) {
    category.setId(id);
    category.setCategoryName(categoryName);
    return category;
  }
}
