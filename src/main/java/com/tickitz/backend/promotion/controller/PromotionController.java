package com.tickitz.backend.promotion.controller;

import com.tickitz.backend.promotion.dto.PromotionDto;
import com.tickitz.backend.promotion.entity.Promotion;
import com.tickitz.backend.promotion.service.PromotionService;
import com.tickitz.backend.response.Response;
import lombok.extern.java.Log;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/promotion")
@Validated
@Log
public class PromotionController {
  private final PromotionService promotionService;

  public PromotionController(PromotionService promotionService) {
    this.promotionService = promotionService;
  }


  @GetMapping
  public ResponseEntity<Response<List<Promotion>>> getAllPromotion(){
    return Response.successResponse("All Promotions Fetched", promotionService.getAllPromotions());
  }

  @GetMapping("/{id}")
  public ResponseEntity<Response<Optional<Promotion>>> getDetailPromotion(@PathVariable Long id) {
    return Response.successResponse("Get Detail Event Success", promotionService.getDetailPromotion(id));
  }

  @PostMapping
  public ResponseEntity<Response<Promotion>> createPromotion(@RequestBody PromotionDto promotionDto) {
    return Response.successResponse("Create Promotion Success", promotionService.createPromotion(promotionDto));
  }

  @PutMapping
  public ResponseEntity<Response<Promotion>> updatePromotion(@RequestBody PromotionDto promotionDto) {
    return Response.successResponse("Update Promotion Success", promotionService.updatePromotion(promotionDto));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Response<Object>> deletePromotion(@PathVariable Long id) {
    return Response.successResponse(promotionService.deletePromotion(id));
  }
}
