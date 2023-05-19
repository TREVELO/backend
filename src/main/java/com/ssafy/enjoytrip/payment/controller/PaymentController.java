package com.ssafy.enjoytrip.payment.controller;

import com.ssafy.enjoytrip.member.model.entity.Member;
import com.ssafy.enjoytrip.payment.model.dto.request.MileageChargeRequestDto;
import com.ssafy.enjoytrip.payment.model.dto.response.MileageChargeResponseDto;
import com.ssafy.enjoytrip.payment.model.service.PaymentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/v1/payment")
@RestController
public class PaymentController {

    private final PaymentService paymentService;

    @PostMapping("/")
    public ResponseEntity<MileageChargeResponseDto> mileageChargeRequest(
        @AuthenticationPrincipal Member member,
        @RequestBody MileageChargeRequestDto mileageChargeRequestDto) {
        log.info("'{}'회원 마일리지 '{}' 원충전 요청 - POST", member.getLoginId(),
            mileageChargeRequestDto.getAmount());
        MileageChargeResponseDto mileageChargeResponseDto = paymentService.verifyMember(member,
            mileageChargeRequestDto);

        return ResponseEntity.ok()
                             .body(mileageChargeResponseDto);
    }

    @GetMapping("/success")
    public ResponseEntity<String> paymentSuccess(@AuthenticationPrincipal Member member,
                                                 @RequestParam("paymentKey") String paymentKey,
                                                 @RequestParam("orderId") String orderId,
                                                 @RequestParam("amount") Long amount) {

        paymentService.verifyRequest(paymentKey, orderId, amount);

        String result = paymentService.approveRequestToPayments(paymentKey, orderId, amount);

        return ResponseEntity.ok().body(result);
    }
}
