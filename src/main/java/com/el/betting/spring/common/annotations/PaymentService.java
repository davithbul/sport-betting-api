package com.el.betting.spring.common.annotations;

import com.el.betting.sdk.v3.account.payment.PaymentSystem;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Profile("dynamicPayment")
@Component
public @interface PaymentService {
    PaymentSystem paymentSystem();
}
