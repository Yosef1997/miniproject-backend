package com.tickitz.backend.promotion.service;

import com.tickitz.backend.promotion.dto.PromotionDto;
import com.tickitz.backend.promotion.entity.Promotion;

import java.util.List;
import java.util.Optional;

public interface PromotionService {
  List<Promotion> getAllPromotions();
  Optional<Promotion> getDetailPromotion(Long id);
  Promotion createPromotion(PromotionDto promotionDto);
  Promotion updatePromotion(PromotionDto promotionDto);
  String deletePromotion(Long id);
}
