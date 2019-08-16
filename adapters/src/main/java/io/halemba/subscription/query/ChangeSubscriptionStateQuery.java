package io.halemba.subscription.query;

import lombok.Builder;
import lombok.NonNull;
import lombok.Value;

@Value
@Builder
public class ChangeSubscriptionStateQuery {
    @NonNull
    String processId;
    @NonNull
    String customerId;
}
