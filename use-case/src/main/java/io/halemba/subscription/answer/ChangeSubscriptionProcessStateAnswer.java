package io.halemba.subscription.answer;

import lombok.Builder;
import lombok.NonNull;
import lombok.Value;

@Value
@Builder
public class ChangeSubscriptionProcessStateAnswer {
    @NonNull String id;
    @NonNull String status;
}
