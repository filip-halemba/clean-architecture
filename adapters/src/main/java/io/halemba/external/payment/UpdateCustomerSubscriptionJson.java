package io.halemba.external.payment;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class UpdateCustomerSubscriptionJson {
    String customerId;
    String subscriptionId;
}
