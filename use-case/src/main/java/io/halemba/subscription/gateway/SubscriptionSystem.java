package io.halemba.subscription.gateway;

import lombok.Builder;
import lombok.Value;

public interface SubscriptionSystem {

    enum SubscriptionSystemStatus {
        OK,
        NOT_OK
    }

    @Value
    @Builder
    class SubscriptionDetailExternalResponse {
        String id;
    }

    @Value
    @Builder
    class CurrentSubscriptionCodeExternalResponse {
        String code;
    }

    SubscriptionSystemStatus check(String customerId);

    SubscriptionDetailExternalResponse changeToPremium(String customerId);

    SubscriptionDetailExternalResponse changeToStandard(String customerId);

    CurrentSubscriptionCodeExternalResponse findForUser(String customerId);

}
