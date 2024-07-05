package com.tickitz.backend.ticket.dto;

import com.tickitz.backend.ticket.entity.Ticket;
import lombok.Data;

@Data
public class UpdateTicketRequestDto {
  private Long id;
  private String name;
  private Integer seats;
  private Long price;

  public Ticket toEntity(Ticket ticket) {
    ticket.setId(id);
    ticket.setName(name);
    ticket.setSeats(seats);
    ticket.setPrice(price);
    return ticket;
  }
}
