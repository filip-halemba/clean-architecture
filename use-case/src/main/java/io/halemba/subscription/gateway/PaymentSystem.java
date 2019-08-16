package io.halemba.subscription.gateway;

public interface PaymentSystem {

    void update(String customerId, String subscriptionId);

}
