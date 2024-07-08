package com.tickitz.backend.ticketOrder.dto;

import com.tickitz.backend.ticketOrder.entity.TicketOrder;
import lombok.Data;

@Data
public class CreateTicketOrderRequestDto {
  private Long orderId;
  private Long ticketId;
  private Long quantity;

  public TicketOrder toEntity() {
    TicketOrder newTicketOrder = new TicketOrder();
    newTicketOrder.setQuantity(quantity);
    return newTicketOrder;
  }
}
