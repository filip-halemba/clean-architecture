package io.halemba.subscription.gateway;

import java.util.UUID;

public interface ChangeSubscriptionIdGenerator {
    UUID generate();
}
