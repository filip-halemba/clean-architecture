package io.halemba.subscription;

import io.vavr.collection.Set;
import lombok.*;

import java.time.Instant;

@Value
@Builder
@With(value = AccessLevel.PRIVATE)
class ChangeSubscriptionProcess {
    @NonNull
    Status status;
    @NonNull
    Instant createTs;
    @NonNull
    ProcessId processId;
    @NonNull
    Instant lastUpdateTs;
    @NonNull
    CustomerId customerId;
    @NonNull
    Set<Agreement> agreements;
    @NonNull
    SubscriptionCode subscriptionCode;

    enum Status {
        REQUESTED,
        CONFIRMED,
        REJECTED
    }

    ChangeSubscriptionProcess confirmed(Instant now) {
        checkState(Status.REQUESTED);
        return this.withStatus(Status.CONFIRMED)
                .withLastUpdateTs(now);
    }

    ChangeSubscriptionProcess rejected(Instant now) {
        checkState(Status.REQUESTED);
        return this.withStatus(Status.REJECTED)
                .withLastUpdateTs(now);
    }

    boolean belongsTo(CustomerId customerId) {
        return this.customerId.equals(customerId);
    }

    private void checkState(Status expected) {
        if (this.status != expected) {
            throw new IllegalStateException(String.format("Cannot transit from: %s.", status));
        }
    }

}
