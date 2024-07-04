package com.tickitz.backend.promotion.dto;

import com.tickitz.backend.event.entity.Event;
import com.tickitz.backend.event.repository.EventRepository;
import com.tickitz.backend.exceptions.applicationException.ApplicationException;
import com.tickitz.backend.promotion.entity.Promotion;
import com.tickitz.backend.types.PromotionTypeEnum;
import lombok.Data;

import java.time.Instant;

@Data
public class CreatePromoRequestDto {
  private String name;
  private PromotionTypeEnum type;
  private Integer usageLimit;
  private Double discount;
  private Instant expiredDate;
  private Long eventId;

  public Promotion toEntity(EventRepository eventRepository) {
    Event event = eventRepository.findById(eventId).orElseThrow(()->new ApplicationException("Event not exists"));
    Promotion newPromo = new Promotion();
    newPromo.setName(name);
    newPromo.setType(type);
    newPromo.setUsageLimit(usageLimit);
    newPromo.setDiscount(discount);
    newPromo.setExpiredDate(expiredDate);
    newPromo.setEvent(event);
    return newPromo;
  }
}
