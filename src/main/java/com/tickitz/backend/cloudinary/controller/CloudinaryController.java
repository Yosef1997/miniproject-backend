package com.tickitz.backend.cloudinary.controller;

import com.tickitz.backend.cloudinary.service.CloudinaryService;
import com.tickitz.backend.response.Response;
import lombok.extern.java.Log;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api/v1/upload-image")
@Log
public class CloudinaryController  {
  private final CloudinaryService cloudinaryService;

  public CloudinaryController(CloudinaryService cloudinaryService) {
    this.cloudinaryService = cloudinaryService;
  }

  @PostMapping("/")
  public ResponseEntity<Response<Object>> handleImageUpload(@RequestParam("file")MultipartFile file) {
    try {
      String imageUrl = cloudinaryService.uploadImage(file);
      return Response.successResponse("Upload Image Success", imageUrl);
    } catch (IOException e) {
      return Response.failedResponse("Upload Image Failed", e);
    }
  }
}
