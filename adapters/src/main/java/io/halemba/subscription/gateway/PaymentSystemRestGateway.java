package io.halemba.subscription.gateway;

import io.halemba.external.payment.PaymentSystemRestFacade;
import io.halemba.external.payment.UpdateCustomerSubscriptionJson;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
class PaymentSystemRestGateway implements PaymentSystem {

    private final PaymentSystemRestFacade paymentSystemRestFacade;

    @Override
    public void update(String customerId, String subscriptionId) {
        paymentSystemRestFacade.updateCustomerSubscription(UpdateCustomerSubscriptionJson.builder()
                                                                                         .customerId(customerId)
                                                                                         .subscriptionId(subscriptionId)
                                                                                         .build());
    }
}
