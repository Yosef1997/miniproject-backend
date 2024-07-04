package com.tickitz.backend.event.dto;

import com.tickitz.backend.promotion.dto.CreatePromoRequestDto;
import com.tickitz.backend.promotion.entity.Promotion;
import com.tickitz.backend.ticket.dao.TicketDao;
import com.tickitz.backend.ticket.dto.CreateTicketRequestDto;
import lombok.Data;

import java.util.List;

@Data
public class CreateEventRequestDto {
  private String eventName;
  private String eventImage;
  private String category;
  private String location;
  private String venue;
  private String description;
  private String date;
  private String startTime;
  private String endTime;
  private Long userId;
  private List<CreateTicketRequestDto> tickets;
  private List<CreatePromoRequestDto> promotions;
}
