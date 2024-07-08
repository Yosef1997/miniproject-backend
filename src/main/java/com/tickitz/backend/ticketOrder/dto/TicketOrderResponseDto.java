package com.tickitz.backend.ticketOrder.dto;

import com.tickitz.backend.ticket.dto.TicketResponseDto;
import lombok.Data;

@Data
public class TicketOrderResponseDto {
  private Long id;
  private Long orderId;
  private TicketResponseDto ticket;
  private Long quantity;
}
