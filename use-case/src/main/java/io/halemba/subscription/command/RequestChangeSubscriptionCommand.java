package io.halemba.subscription.command;

import io.halemba.subscription.CustomerId;
import io.halemba.subscription.SubscriptionCode;
import lombok.Builder;
import lombok.NonNull;
import lombok.Value;

@Value
@Builder
public class RequestChangeSubscriptionCommand {
    @NonNull
    CustomerId customerId;
    @NonNull
    SubscriptionCode subscriptionCode;
}
