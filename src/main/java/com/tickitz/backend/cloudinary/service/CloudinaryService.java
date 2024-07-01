package com.tickitz.backend.cloudinary.service;

import com.tickitz.backend.cloudinary.dto.CloudinaryUploadResponseDto;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface CloudinaryService {
  CloudinaryUploadResponseDto uploadImage(MultipartFile file) throws IOException;

  CloudinaryUploadResponseDto updateImage(MultipartFile file, String publicId) throws IOException;
}
