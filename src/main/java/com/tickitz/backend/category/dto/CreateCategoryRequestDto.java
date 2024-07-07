package com.tickitz.backend.category.dto;

import com.tickitz.backend.category.entity.Category;
import lombok.Data;

@Data
public class CreateCategoryRequestDto {
  private String categoryName;

  public Category toEntity(){
    Category newCategory = new Category();
    newCategory.setCategoryName(categoryName);
    return newCategory;
  }
}
