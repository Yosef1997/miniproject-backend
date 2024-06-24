package com.tickitz.backend.cloudinary.service;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;

import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

@Service
@Log
public class CloudinaryServiceImpl implements CloudinaryService {
  private final Cloudinary cloudinary;

  public CloudinaryServiceImpl(@Value("${cloudinary.cloud_name}") String cloudName,
                               @Value("${cloudinary.api_key}") String apiKey,
                               @Value("${cloudinary.api_secret}") String apiSecret) {
    this.cloudinary = new Cloudinary(ObjectUtils.asMap(
            "cloud_name", cloudName,
            "api_key", apiKey,
            "api_secret", apiSecret

    ));
  }
  @Override
  public String uploadImage(MultipartFile file) throws IOException {
    Map<?,?> uploadResult = cloudinary.uploader().upload(file.getBytes(), ObjectUtils.emptyMap());
    return uploadResult.get("secure_url").toString();
  }
}
