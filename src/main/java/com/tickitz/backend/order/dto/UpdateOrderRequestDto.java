package com.tickitz.backend.order.dto;

import com.tickitz.backend.order.entity.Order;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class UpdateOrderRequestDto {
  private Long id;
  private BigDecimal totalPrice;
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
