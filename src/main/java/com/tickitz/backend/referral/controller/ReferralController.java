package com.tickitz.backend.referral.controller;

import com.tickitz.backend.referral.dto.CreateReferralRequestDto;
import com.tickitz.backend.referral.dto.ReferralResponseDto;
import com.tickitz.backend.referral.dto.UpdateReferralRequestDto;
import com.tickitz.backend.referral.service.ReferralService;
import com.tickitz.backend.response.Response;
import lombok.extern.java.Log;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/referral")
@Validated
@Log
public class ReferralController {
  private final ReferralService referralService;

  public ReferralController(ReferralService referralService) {
    this.referralService = referralService;
  }

  @GetMapping
  public ResponseEntity<Response<List<ReferralResponseDto>>> getAllReferral() {
    return Response.successResponse("All Referral fetched", referralService.getAllReferral());
  }

  @PostMapping
  public ResponseEntity<Response<ReferralResponseDto>> createReferral(@RequestBody CreateReferralRequestDto createReferralRequestDto) {
    return Response.successResponse("Add Referral success to user id " + createReferralRequestDto.getUserId(), referralService.createReferral(createReferralRequestDto));
  }

  @GetMapping("/user/{id}")
  public ResponseEntity<Response<Object>> getUserReferral(@PathVariable Long id) {
    return Response.successResponse("Referral user with id " + id, referralService.getReferralUser(id));
  }

  @PutMapping
  public ResponseEntity<Response<ReferralResponseDto>> updateReferral(@RequestBody UpdateReferralRequestDto requestDto) {
    return Response.successResponse("Update referral success", referralService.updateReferral(requestDto));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Response<Object>> deleteReferral(@PathVariable Long id) {
    return Response.successResponse(referralService.deleteReferral(id));
  }
}
