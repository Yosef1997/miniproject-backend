package com.tickitz.backend.order.service.impl;

import com.tickitz.backend.event.service.EventService;
import com.tickitz.backend.exceptions.applicationException.ApplicationException;
import com.tickitz.backend.order.dto.CreateOrderRequestDto;
import com.tickitz.backend.order.dto.OrderResponseDto;
import com.tickitz.backend.order.dto.UpdateOrderRequestDto;
import com.tickitz.backend.order.entity.Order;
import com.tickitz.backend.order.repository.OrderRepository;
import com.tickitz.backend.order.service.OrderService;
import com.tickitz.backend.promotion.service.PromotionService;
import com.tickitz.backend.ticketOrder.dto.CreateTicketOrderRequestDto;
import com.tickitz.backend.ticketOrder.service.TicketOrderService;
import com.tickitz.backend.users.service.UsersService;
import lombok.extern.java.Log;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Log
public class OrderServiceImpl implements OrderService {
  private final OrderRepository orderRepository;

  private final UsersService usersService;

  private final EventService eventService;

  private final TicketOrderService ticketOrderService;
  private final PromotionService promotionService;

  public OrderServiceImpl(OrderRepository orderRepository, UsersService usersService,
                          EventService eventService, @Lazy TicketOrderService ticketOrderService,
                          PromotionService promotionService) {
    this.orderRepository = orderRepository;
    this.usersService = usersService;
    this.eventService = eventService;
    this.ticketOrderService = ticketOrderService;
    this.promotionService = promotionService;
  }

  @Override
  public Page<OrderResponseDto> getAllOrders(Pageable pageable) {
    return orderRepository.findAll(pageable).map(this::mapToOrderResponseDto);
  }

  @Override
  public OrderResponseDto getDetailOrder(Long id) {
    Order detail = orderRepository.findById(id).orElseThrow(() -> new ApplicationException("Order with id: " + id + " not exists"));
    return mapToOrderResponseDto(detail);

  }

  @Override
  public Order getDetail(Long id) {
    return orderRepository.findById(id).orElseThrow(() -> new ApplicationException("Order with id: " + id + " not found"));
  }

  @Override
  public List<Long> getOrderPromo(Long orderId) {
    return orderRepository.findAllPromoIdByOrderId(orderId);
  }

  @Override
  public OrderResponseDto createOrder(CreateOrderRequestDto createOrderRequestDto) {
    Order data = createOrderRequestDto.toEntity();
    data.setUser(usersService.getDetailUserId(createOrderRequestDto.getUserId()));
    data.setOrganizer(usersService.getDetailUserId(createOrderRequestDto.getOrganizerId()));
    data.setEvent(eventService.getDetail(createOrderRequestDto.getEventId()));
    data.setPromotions(promotionService.getAllPromoById(createOrderRequestDto.getPromoIds()));
    Order result = orderRepository.save(data);

    for (CreateTicketOrderRequestDto ticketOrder : createOrderRequestDto.getTickets()) {
      ticketOrder.setOrderId(result.getId());
      ticketOrderService.createTicketOrder(ticketOrder);
    }

    return mapToOrderResponseDto(result);
  }

  @Override
  public OrderResponseDto updateOrder(UpdateOrderRequestDto updateOrderRequestDto) {
    Order detail = orderRepository.findById(updateOrderRequestDto.getId()).orElseThrow(() -> new ApplicationException("Order with id: " + updateOrderRequestDto.getId() + " not exists"));
    Order updated = orderRepository.save(updateOrderRequestDto.toEntity(detail));
    return mapToOrderResponseDto(updated);
  }

  @Override
  public String deleteOrder(Long id) {
    getDetailOrder(id);
    orderRepository.deleteById(id);
    return "Delete Order Success";
  }

  public OrderResponseDto mapToOrderResponseDto(Order order) {
    OrderResponseDto response = new OrderResponseDto();
    response.setId(order.getId());
    response.setTotalPrice(order.getTotalPrice());
    response.setUsedPoint(order.getUsedPoint());
    response.setUser(usersService.getDetailUser(order.getUser().getEmail()));
    response.setOrganizer(usersService.getDetailUser(order.getOrganizer().getEmail()));
    response.setEvent(eventService.getDetailEvent(order.getEvent().getId()));
    response.setTickets(ticketOrderService.getAllTicketOrders(order.getId()));
    response.setPromotions(order.getPromotions());
    return response;
  }
}
