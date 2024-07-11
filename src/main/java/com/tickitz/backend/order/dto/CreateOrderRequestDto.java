package com.tickitz.backend.order.dto;

import com.tickitz.backend.order.entity.Order;
import com.tickitz.backend.ticket.dto.TicketResponseDto;
import com.tickitz.backend.ticketOrder.dto.CreateTicketOrderRequestDto;
import lombok.Data;

import java.util.List;

@Data
public class CreateOrderRequestDto {
  private Long totalPrice;
  private Integer totalTicket;
  private Long usedPoint;
  private Long userId;
  private Long organizerId;
  private Long eventId;
  private List<CreateTicketOrderRequestDto> tickets;
  private List<Long> promoIds;

  public Order toEntity() {
    Order newOrder = new Order();
    newOrder.setTotalPrice(totalPrice);
    newOrder.setTotalTicket(totalTicket);
    newOrder.setUsedPoint(usedPoint);
    return newOrder;
  }
}
