package com.tickitz.backend.promotion.controller;

import com.tickitz.backend.promotion.dao.PromotionDao;
import com.tickitz.backend.promotion.dto.CreatePromoRequestDto;
import com.tickitz.backend.promotion.dto.PromoResponseDto;
import com.tickitz.backend.promotion.dto.UpdatePromoRequestDto;
import com.tickitz.backend.promotion.service.PromotionService;
import com.tickitz.backend.response.Response;
import lombok.extern.java.Log;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
  public ResponseEntity<Response<List<PromotionDao>>> getAllPromotion(@RequestParam(name = "eventId",required = false) Long eventId){
    return Response.successResponse("All Promotions Fetched", promotionService.getAllPromotions(eventId));
  }

  @GetMapping("/{id}")
  public ResponseEntity<Response<PromoResponseDto>> getDetailPromotion(@PathVariable Long id) {
    return Response.successResponse("Get Detail Event Success", promotionService.getDetailPromotion(id));
  }

  @PostMapping
  public ResponseEntity<Response<PromoResponseDto>> createPromotion(@RequestBody CreatePromoRequestDto createPromoRequestDto) {
    return Response.successResponse("Create Promotion Success", promotionService.createPromotion(createPromoRequestDto));
  }

  @PutMapping
  public ResponseEntity<Response<PromoResponseDto>> updatePromotion(@RequestBody UpdatePromoRequestDto updatePromoRequestDto) {
    return Response.successResponse("Update Promotion Success", promotionService.updatePromotion(updatePromoRequestDto));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Response<Object>> deletePromotion(@PathVariable Long id) {
    return Response.successResponse(promotionService.deletePromotion(id));
  }
}
