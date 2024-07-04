package com.tickitz.backend.promotion.service;

import com.tickitz.backend.promotion.dao.PromotionDao;
import com.tickitz.backend.promotion.dto.CreatePromoRequestDto;
import com.tickitz.backend.promotion.dto.PromoResponseDto;
import com.tickitz.backend.promotion.dto.UpdatePromoRequestDto;
import com.tickitz.backend.promotion.entity.Promotion;

import java.util.List;
import java.util.Optional;

public interface PromotionService {
  List<PromotionDao> getAllPromotions(Long eventId);
  PromoResponseDto getDetailPromotion(Long id);
  PromoResponseDto createPromotion(CreatePromoRequestDto createPromoRequestDto);
  PromoResponseDto updatePromotion(UpdatePromoRequestDto updatePromoRequestDto);
  String deletePromotion(Long id);
}
