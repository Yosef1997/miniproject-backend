package com.tickitz.backend.point.controller;

import com.tickitz.backend.point.dao.ResponsePointDao;
import com.tickitz.backend.point.dto.PointRequestDto;
import com.tickitz.backend.point.dto.PointResponseDto;
import com.tickitz.backend.point.service.PointService;
import com.tickitz.backend.response.Response;
import lombok.extern.java.Log;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/point")
@Validated
@Log
public class PointController {
  private final PointService pointService;

  public PointController(PointService pointService) {
    this.pointService = pointService;
  }

  @GetMapping
  public ResponseEntity<Response<List<ResponsePointDao>>> getAllPoints() {
    return Response.successResponse("Points data fetched", pointService.getAllPoints());
  }

  @PostMapping
  public ResponseEntity<Response<PointResponseDto>> createPoint(@RequestBody PointRequestDto requestDto) {
    return Response.successResponse("Add Point success to user id " + requestDto.getId(), pointService.createPoints(requestDto));
  }

  @GetMapping("/{id}")
  public ResponseEntity<Response<Object>> getUserPoint(@PathVariable("id") Long id) {
    return Response.successResponse("Total point with user id " + id, pointService.getPointUser(id));
  }
}
