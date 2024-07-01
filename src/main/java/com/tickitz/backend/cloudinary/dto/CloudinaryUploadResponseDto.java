package com.tickitz.backend.cloudinary.dto;

import lombok.Data;

@Data
public class CloudinaryUploadResponseDto {
  private String publicId;
  private String url;
}
