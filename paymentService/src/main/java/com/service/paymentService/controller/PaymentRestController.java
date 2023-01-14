package com.service.paymentService.controller;

import com.service.domainPersistence.payload.payment.PaymentRequest;
import com.service.domainPersistence.payload.payment.PaymentResponse;
import com.service.paymentService.service.PaymentServiceGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "payment")
@RequiredArgsConstructor
public class PaymentRestController {

    private final PaymentServiceGateway gateway;

    @PostMapping(value = "createBalance")
    public Mono<PaymentResponse> createBalance(@Valid @RequestBody PaymentRequest paymentRequest) {
        PaymentResponse response = new PaymentResponse();
        return Mono.from(gateway.createCreditUser(paymentRequest).flatMap(
                res -> {
                    response.setPaymentId(res.getPaymentId());
                    response.setUserId(res.getUserId());
                    response.setCreditName(res.getCreditName());
                    response.setCreditNumber(res.getCreditNumber());
                    response.setCreditAmount(res.getCreditAmount());
                    response.setCreditStatus(res.getCreditStatus());
                    return Mono.just(response);
                }
        ).switchIfEmpty(Mono.empty()));
    }

}

// create payment credit balance
// update payment credit balance
// deduct payment credit balance

// create payment historical data with status payment approved or not
