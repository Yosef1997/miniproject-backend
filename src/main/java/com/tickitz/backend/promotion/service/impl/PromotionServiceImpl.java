package com.tickitz.backend.promotion.service.impl;

import com.tickitz.backend.event.repository.EventRepository;
import com.tickitz.backend.exceptions.applicationException.ApplicationException;
import com.tickitz.backend.promotion.dao.PromotionDao;
import com.tickitz.backend.promotion.dto.CreatePromoRequestDto;
import com.tickitz.backend.promotion.dto.PromoResponseDto;
import com.tickitz.backend.promotion.dto.UpdatePromoRequestDto;
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
  private final EventRepository eventRepository;

  public PromotionServiceImpl(PromotionRepository promotionRepository, EventRepository eventRepository) {
    this.promotionRepository = promotionRepository;
    this.eventRepository = eventRepository;
  }

  @Override
  public List<PromotionDao> getAllPromotions(Long eventId) {
    if (eventId != null) {
      return promotionRepository.findAllPromotionByEventId(eventId);
    }
    return promotionRepository.findAllPromotion();
  }

  @Override
  public PromoResponseDto getDetailPromotion(Long id) {
    Promotion detail = promotionRepository.findById(id).orElseThrow(()->new ApplicationException("Promotion not exists"));
    PromoResponseDto response = new PromoResponseDto();
    response.setId(detail.getId());
    response.setName(detail.getName());
    response.setType(detail.getType());
    response.setUsageLimit(detail.getUsageLimit());
    response.setExpiredDate(detail.getExpiredDate());
    response.setEventId(detail.getEvent().getId());
    return response;
  }

  @Override
  public PromoResponseDto createPromotion(CreatePromoRequestDto createPromoRequestDto) {
    Promotion newPromotion = promotionRepository.save(createPromoRequestDto.toEntity(eventRepository));
    PromoResponseDto response = new PromoResponseDto();
    response.setId(newPromotion.getId());
    response.setName(newPromotion.getName());
    response.setType(newPromotion.getType());
    response.setUsageLimit(newPromotion.getUsageLimit());
    response.setDiscount(newPromotion.getDiscount());
    response.setExpiredDate(newPromotion.getExpiredDate());
    response.setEventId(newPromotion.getEvent().getId());
    return response;
  }

  @Override
  public PromoResponseDto updatePromotion(UpdatePromoRequestDto updatePromoRequestDto) {
    Promotion promotion = promotionRepository.findById(updatePromoRequestDto.getId()).orElseThrow(() -> new ApplicationException("Promotion not exists"));
    promotion.setName(promotion.getName());
    promotion.setUsageLimit(promotion.getUsageLimit());
    promotion.setDiscount(promotion.getDiscount());
    promotion.setExpiredDate(promotion.getExpiredDate());
    Promotion updated = promotionRepository.save(promotion);

    PromoResponseDto response = new PromoResponseDto();
    response.setEventId(updated.getId());
    response.setName(updated.getName());
    response.setType(updated.getType());
    response.setUsageLimit(updated.getUsageLimit());
    response.setExpiredDate(updated.getExpiredDate());
    response.setEventId(updated.getEvent().getId());
    return response;
  }

  @Override
  public String deletePromotion(Long id) {
    promotionRepository.deleteById(id);
    return "Delete Promotion Success";
  }
}
