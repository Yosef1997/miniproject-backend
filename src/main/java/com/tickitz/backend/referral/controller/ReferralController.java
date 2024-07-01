package com.tickitz.backend.referral.controller;

import com.tickitz.backend.referral.dao.ResponseReferralDao;
import com.tickitz.backend.referral.dto.ReferralRequestDto;
import com.tickitz.backend.referral.dto.ReferralResponseDto;
import com.tickitz.backend.referral.dto.UpdateRequestDto;
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
  public ResponseEntity<Response<List<ResponseReferralDao>>> getAllReferral() {
    return Response.successResponse("All Referral fetched", referralService.getAllReferral());
  }

  @PostMapping
  public ResponseEntity<Response<ReferralResponseDto>> createReferral(@RequestBody ReferralRequestDto referralRequestDto) {
    return Response.successResponse("Add Referral success to user id " + referralRequestDto.getId(), referralService.createReferral(referralRequestDto));
  }

  @GetMapping("/{id}")
  public ResponseEntity<Response<Object>> getUserReferral(@PathVariable Long id) {
    return Response.successResponse("Referral user with id " + id, referralService.getReferralUser(id));
  }

  @PutMapping
  public ResponseEntity<Response<String>> updateReferral(@RequestBody UpdateRequestDto requestDto) {
    return Response.successResponse("Update referral success", referralService.updateReferral(requestDto));
  }
}
