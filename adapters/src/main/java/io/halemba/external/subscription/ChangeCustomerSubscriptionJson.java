package io.halemba.external.subscription;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class ChangeCustomerSubscriptionJson {
    String customerId;
    String subscriptionType;
}
