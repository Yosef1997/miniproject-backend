package com.tickitz.backend.promotion.service.impl;

import com.tickitz.backend.exceptions.applicationException.ApplicationException;
import com.tickitz.backend.promotion.dto.PromotionDto;
import com.tickitz.backend.promotion.entity.Promotion;
import com.tickitz.backend.promotion.repository.PromotionRepository;
import com.tickitz.backend.promotion.service.PromotionService;
import lombok.extern.java.Log;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Log
public class PromotionServiceImpl implements PromotionService {
  private final PromotionRepository promotionRepository;

  public PromotionServiceImpl(PromotionRepository promotionRepository) {
    this.promotionRepository = promotionRepository;
  }

  @Override
  public List<Promotion> getAllPromotions() {
    return promotionRepository.findAll();
  }

  @Override
  public Optional<Promotion> getDetailPromotion(Long id) {
    return promotionRepository.findById(id);
  }

  @Override
  public Promotion createPromotion(PromotionDto promotionDto) {
    Promotion newPromotion = new Promotion();
    newPromotion.setName(promotionDto.getName());
    newPromotion.setType(promotionDto.getType());
    newPromotion.setUsageLimit(promotionDto.getUsageLimit());
    newPromotion.setDiscount(promotionDto.getDiscount());
    newPromotion.setExpiredDate(promotionDto.getExpiredDate());
    newPromotion.setEventId(promotionDto.getEventId());
    return promotionRepository.save(newPromotion);
  }

  @Override
  public Promotion updatePromotion(PromotionDto promotionDto) {
    Promotion promotion = getDetailPromotion(promotionDto.getId()).orElseThrow(()->new ApplicationException("Promotion not exists"));
    promotion.setName(promotionDto.getName());
    promotion.setName(promotionDto.getName());
    promotion.setType(promotionDto.getType());
    promotion.setUsageLimit(promotionDto.getUsageLimit());
    promotion.setDiscount(promotionDto.getDiscount());
    promotion.setExpiredDate(promotionDto.getExpiredDate());
    promotion.setEventId(promotionDto.getEventId());
    return promotionRepository.save(promotion);
  }

  @Override
  public String deletePromotion(Long id) {
    promotionRepository.deleteById(id);
    return "Delete Promotion Success";
  }
}
