package io.halemba.subscription.rest.response;

import lombok.Builder;
import lombok.NonNull;
import lombok.Value;

@Value
@Builder
public class ChangeSubscriptionStateResponseBody {
    @NonNull
    String processId;
    @NonNull
    String status;
}
