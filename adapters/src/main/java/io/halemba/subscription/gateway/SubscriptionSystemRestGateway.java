package io.halemba.subscription.gateway;

import io.halemba.external.subscription.ChangeCustomerSubscriptionJson;
import io.halemba.external.subscription.SubscriptionSystemRestFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
class SubscriptionSystemRestGateway implements SubscriptionSystem {

    private final SubscriptionSystemRestFacade subscriptionSystemRestFacade;

    @Override
    public SubscriptionSystemStatus check(String customerId) {
        return SubscriptionSystemStatus.valueOf(subscriptionSystemRestFacade.checkCustomerStatus(customerId));
    }

    @Override
    public SubscriptionDetailExternalResponse changeToPremium(String customerId) {
        return changeSubscriptionTo(customerId, "PREMIUM");
    }

    @Override
    public SubscriptionDetailExternalResponse changeToStandard(String customerId) {
        return changeSubscriptionTo(customerId, "STANDARD");
    }

    private SubscriptionDetailExternalResponse changeSubscriptionTo(String customerId, String type) {
        return SubscriptionDetailExternalResponse.builder()
                                                 .id(subscriptionSystemRestFacade.changeSubscription(
                                                         ChangeCustomerSubscriptionJson.builder()
                                                                                       .customerId(customerId)
                                                                                       .subscriptionType(type)
                                                                                       .build()))
                                                 .build();
    }

    @Override
    public CurrentSubscriptionCodeExternalResponse findForUser(String customerId) {
        return CurrentSubscriptionCodeExternalResponse.builder()
                .code(subscriptionSystemRestFacade.findSubscriptionFor(customerId))
                .build();
    }

}
