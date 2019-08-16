package io.halemba.external.payment;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class PaymentSystemRestFacade {

    public void updateCustomerSubscription(UpdateCustomerSubscriptionJson body) {
        log.info("PaymentSystemRestFacade.updateCustomerSubscription with body = {}", body);
    }

}
