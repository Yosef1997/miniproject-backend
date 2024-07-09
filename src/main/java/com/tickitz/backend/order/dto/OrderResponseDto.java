package com.tickitz.backend.order.dto;

import com.tickitz.backend.event.dto.EventResponseDto;
import com.tickitz.backend.promotion.dto.PromoResponseDto;
import com.tickitz.backend.promotion.entity.Promotion;
import com.tickitz.backend.ticket.dto.TicketResponseDto;
import com.tickitz.backend.ticketOrder.dto.TicketOrderResponseDto;
import com.tickitz.backend.users.dto.ResponseUserDto;
import lombok.Data;

import java.util.List;

@Data
public class OrderResponseDto {
  private Long id;
  private Long totalPrice;
  private Long usedPoint;
  private ResponseUserDto user;
  private ResponseUserDto organizer;
  private EventResponseDto event;
  private List<TicketOrderResponseDto> tickets;
  private List<Promotion> promotions;
}
