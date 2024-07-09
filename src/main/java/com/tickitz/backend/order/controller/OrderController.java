package com.tickitz.backend.order.controller;

import com.tickitz.backend.order.dto.CreateOrderRequestDto;
import com.tickitz.backend.order.dto.OrderResponseDto;
import com.tickitz.backend.order.dto.UpdateOrderRequestDto;
import com.tickitz.backend.order.service.OrderService;
import com.tickitz.backend.response.Response;
import lombok.extern.java.Log;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/v1/order")
@Validated
@Log
public class OrderController {
  private final OrderService orderService;

  public OrderController(OrderService orderService) {
    this.orderService = orderService;
  }

  @GetMapping
  public ResponseEntity<Response<Page<OrderResponseDto>>> getAllOrders(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
    Pageable pageable = PageRequest.of(page,size);
    return Response.successResponse("All Order Fetched", orderService.getAllOrders(pageable));
  }

  @GetMapping("/{id}")
  public ResponseEntity<Response<OrderResponseDto>> getDetailOrder(@PathVariable Long id) {
    return Response.successResponse("Detail Order id: " + id, orderService.getDetailOrder(id));
  }

  @PostMapping
  public ResponseEntity<Response<OrderResponseDto>> createOrder(@RequestBody CreateOrderRequestDto createOrderRequestDto) {
    return Response.successResponse("Create Order Success", orderService.createOrder(createOrderRequestDto));
  }

  @PutMapping
  public ResponseEntity<Response<OrderResponseDto>> updateOrder(@RequestBody UpdateOrderRequestDto updateOrderRequestDto) {
    return Response.successResponse("Update Order Success", orderService.updateOrder(updateOrderRequestDto));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Response<Object>> deleteOrder(@PathVariable Long id) {
    return Response.successResponse(orderService.deleteOrder(id));
  }
}
