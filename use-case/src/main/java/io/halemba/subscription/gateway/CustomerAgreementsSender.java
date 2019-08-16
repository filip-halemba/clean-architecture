package io.halemba.subscription.gateway;

import io.vavr.collection.Set;
import lombok.Builder;
import lombok.Value;

public interface CustomerAgreementsSender {

    @Value
    @Builder
    class MessageExternalCommand {
        String content;
        String customerId;
        Set<String> agreements;
    }

    void send(MessageExternalCommand command);

}
