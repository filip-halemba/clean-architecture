package io.halemba.subscription.gateway;

import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
class IdGenerator implements ChangeSubscriptionIdGenerator {

    @Override
    public UUID generate() {
        return UUID.randomUUID();
    }

}
