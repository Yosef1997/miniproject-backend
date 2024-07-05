package com.tickitz.backend.promotion.service.impl;


import com.tickitz.backend.event.service.EventService;
import com.tickitz.backend.exceptions.applicationException.ApplicationException;
import com.tickitz.backend.promotion.dto.CreatePromoRequestDto;
import com.tickitz.backend.promotion.dto.PromoResponseDto;
import com.tickitz.backend.promotion.dto.UpdatePromoRequestDto;
import com.tickitz.backend.promotion.entity.Promotion;
import com.tickitz.backend.promotion.repository.PromotionRepository;
import com.tickitz.backend.promotion.service.PromotionService;
import lombok.extern.java.Log;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Log
public class PromotionServiceImpl implements PromotionService {
  private final PromotionRepository promotionRepository;
  private final EventService eventService;

  public PromotionServiceImpl(PromotionRepository promotionRepository, EventService eventService) {
    this.promotionRepository = promotionRepository;
    this.eventService = eventService;
  }

  @Override
  public List<PromoResponseDto> getAllPromotions(Long eventId) {
    if (eventId == null) {
      List<Promotion> result = promotionRepository.findAll();
      return result.stream().map(this::mapToPromoResponseDto).collect(Collectors.toList());
    }
    List<Promotion> result = promotionRepository.findAllByEventId(eventId);
    return result.stream().map(this::mapToPromoResponseDto).collect(Collectors.toList());
  }

  @Override
  public PromoResponseDto getDetailPromotion(Long id) {
    Promotion detail = promotionRepository.findById(id).orElseThrow(()->new ApplicationException("Promotion not exists"));
    return mapToPromoResponseDto(detail);
  }

  @Override
  public PromoResponseDto createPromotion(CreatePromoRequestDto createPromoRequestDto) {
    Promotion newPromotion = promotionRepository.save(createPromoRequestDto.toEntity(eventService));
    return mapToPromoResponseDto(newPromotion);
  }

  @Override
  public PromoResponseDto updatePromotion(UpdatePromoRequestDto updatePromoRequestDto) {
    Promotion promotion = promotionRepository.findById(updatePromoRequestDto.getId()).orElseThrow(() -> new ApplicationException("Promotion not exists"));
    Promotion updated = promotionRepository.save(updatePromoRequestDto.toEntity(promotion));
    return mapToPromoResponseDto(updated);
  }

  @Override
  public String deletePromotion(Long id) {
    getDetailPromotion(id);
    promotionRepository.deleteById(id);
    return "Delete Promotion Success";
  }

  public PromoResponseDto mapToPromoResponseDto(Promotion promo) {
    PromoResponseDto response = new PromoResponseDto();
    response.setId(promo.getId());
    response.setName(promo.getName());
    response.setType(promo.getType());
    response.setUsageLimit(promo.getUsageLimit());
    response.setDiscount(promo.getDiscount());
    response.setExpiredDate(promo.getExpiredDate());
    response.setEventId(promo.getEvent().getId());
    return response;
  }
}
