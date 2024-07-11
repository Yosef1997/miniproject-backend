package com.tickitz.backend.order.dto;

import com.tickitz.backend.order.entity.Order;
import lombok.Data;

@Data
public class UpdateOrderRequestDto {
  private Long id;
  private Long totalPrice;
  private Integer totalTicket;
  private Long usedPoint;

  public Order toEntity(Order order) {
    order.setId(id);
    order.setTotalPrice(totalPrice);
    order.setTotalTicket(totalTicket);
    order.setUsedPoint(usedPoint);
    return order;
  }
}
