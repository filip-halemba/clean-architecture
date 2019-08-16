package io.halemba.subscription;

import io.halemba.subscription.gateway.DocumentStore;
import io.halemba.subscription.gateway.DocumentStore.DocumentExternalResponse;
import io.vavr.collection.HashMap;
import io.vavr.collection.Map;
import io.vavr.collection.Seq;
import io.vavr.collection.Set;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
class AgreementFactory {

    private final DocumentStore documentStore;

    Set<Agreement> create(@NonNull SubscriptionCode subscriptionCode) {
        return getAgreementsFor(subscriptionCode).map(this::toAgreement)
                                                 .toSet();
    }

    private Agreement toAgreement(DocumentExternalResponse response) {
        return Agreement.builder()
                .code(response.getCode())
                .isMandatory(response.getIsMandatory())
                .version(response.getVersion())
                .build();
    }

    private Seq<DocumentExternalResponse> getAgreementsFor(SubscriptionCode subscriptionCode) {
        return documentStore.search(prepareQuery(subscriptionCode));
    }

    private Map<String, String> prepareQuery(SubscriptionCode subscriptionCode) {
        return HashMap.of(
                "type", "AGREEMENT",
                "subscriptionCode", subscriptionCode.getValueAsString()
        );
    }

}
