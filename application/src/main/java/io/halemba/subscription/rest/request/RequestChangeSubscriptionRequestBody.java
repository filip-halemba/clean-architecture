package io.halemba.subscription.rest.request;

import lombok.Value;

import javax.validation.constraints.NotEmpty;

@Value
public class RequestChangeSubscriptionRequestBody {
    @NotEmpty
    String customerId;
    @NotEmpty
    String subscriptionCode;
}
