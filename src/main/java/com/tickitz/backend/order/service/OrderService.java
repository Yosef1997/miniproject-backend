package com.tickitz.backend.order.service;

import com.tickitz.backend.order.dto.CreateOrderRequestDto;
import com.tickitz.backend.order.dto.OrderResponseDto;
import com.tickitz.backend.order.dto.UpdateOrderRequestDto;
import com.tickitz.backend.order.entity.Order;

import java.util.List;

public interface OrderService {
List<OrderResponseDto> getAllOrders();
OrderResponseDto getDetailOrder(Long id);
Order getDetail(Long id);
List<Long> getOrderPromo(Long orderId);
OrderResponseDto createOrder(CreateOrderRequestDto createOrderRequestDto);
OrderResponseDto updateOrder(UpdateOrderRequestDto updateOrderRequestDto);
String deleteOrder(Long id);
}
