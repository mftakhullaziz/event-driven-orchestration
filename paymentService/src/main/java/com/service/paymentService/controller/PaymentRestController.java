package com.service.paymentService.controller;

import com.service.domainPersistence.payload.payment.PaymentRequest;
import com.service.domainPersistence.payload.payment.PaymentResponse;
import com.service.paymentService.service.PaymentServiceGateway;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

@RestController
@Api(tags = "Payment Rest API")
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

    @PostMapping(value = "addCreditBalance")
    public Mono<PaymentResponse> addCreditBalance(@Valid @RequestBody PaymentRequest paymentRequest) {
        PaymentResponse response = new PaymentResponse();
        return Mono.from(gateway.addCreditUser(paymentRequest).flatMap(
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

    @PostMapping(value = "deductCreditBalance")
    public Mono<PaymentResponse> deductCreditBalance(@Valid @RequestBody PaymentRequest paymentRequest) {
        PaymentResponse response = new PaymentResponse();
        return Mono.from(gateway.deductCreditUser(paymentRequest).flatMap(
                res -> {
                    response.setPaymentId(res.getT1().getPaymentId());
                    response.setUserId(res.getT1().getUserId());
                    response.setCreditName(res.getT1().getCreditName());
                    response.setCreditNumber(res.getT1().getCreditNumber());
                    response.setCreditAmount(res.getT1().getCreditAmount());
                    response.setCreditStatus(res.getT1().getCreditStatus());
                    return Mono.just(response);
                }
        ).switchIfEmpty(Mono.empty()));
    }

}

// create payment historical data with status payment approved or not
