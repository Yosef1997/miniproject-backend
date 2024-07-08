package com.tickitz.backend.ticketOrder.dto;

import com.tickitz.backend.ticketOrder.entity.TicketOrder;
import lombok.Data;

@Data
public class UpdateTicketOrderRequestDto {
  private Long id;
  private Long quantity;

  public TicketOrder toEntity(TicketOrder ticketOrder) {
    ticketOrder.setId(id);
    ticketOrder.setQuantity(quantity);
    return ticketOrder;
  }
}
