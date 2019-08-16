package io.halemba.subscription;


import io.halemba.subscription.gateway.PaymentSystem;
import io.halemba.subscription.gateway.SubscriptionSystem;
import io.halemba.subscription.gateway.SubscriptionSystem.SubscriptionDetailExternalResponse;
import lombok.RequiredArgsConstructor;

import java.util.function.Function;

@RequiredArgsConstructor
class SubscriptionInformationUpdater {
    private final SubscriptionSystem subscriptionSystem;
    private final PaymentSystem paymentSystem;

    void changeFor(ChangeSubscriptionProcess process) {
        String customerIdAsString = process.getCustomerId().getValueAsString();
        SubscriptionDetailExternalResponse response = resolveAction(process).apply(customerIdAsString);
        paymentSystem.update(customerIdAsString, response.getId());
    }

    private Function<String, SubscriptionDetailExternalResponse> resolveAction(ChangeSubscriptionProcess process) {
        if (process.getSubscriptionCode().getValue().equals(SubscriptionCode.Value.PREMIUM)) {
            return subscriptionSystem::changeToPremium;
        } else if (process.getSubscriptionCode().getValue().equals(SubscriptionCode.Value.STANDARD)) {
            return subscriptionSystem::changeToStandard;
        } else {
            throw new UnsupportedOperationException();
        }
    }

}
