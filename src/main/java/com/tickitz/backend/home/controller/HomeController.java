package com.tickitz.backend.home.controller;

import com.tickitz.backend.home.dto.HomeResponseDto;
import com.tickitz.backend.home.service.HomeService;
import com.tickitz.backend.response.Response;
import lombok.extern.java.Log;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/home")
@Validated
@Log
public class HomeController {
  private final HomeService homeService;

  public HomeController(HomeService homeService) {
    this.homeService = homeService;
  }

  @GetMapping
  public ResponseEntity<Response<HomeResponseDto>> getHome(){
    return Response.successResponse("Home Data Fetched", homeService.getHome());
  }
}
