package io.halemba.subscription;


import lombok.RequiredArgsConstructor;

import java.time.Instant;

@RequiredArgsConstructor
class ChangeSubscriptionProcessFactory {

    private final AgreementFactory agreementFactory;

    ChangeSubscriptionProcess create(ProcessId processId,
                                     CustomerId customerId,
                                     SubscriptionCode subscriptionCode) {
        return ChangeSubscriptionProcess.builder()
                .status(ChangeSubscriptionProcess.Status.REQUESTED)
                .processId(processId)
                .createTs(Instant.now())
                .lastUpdateTs(Instant.now())
                .customerId(customerId)
                .subscriptionCode(subscriptionCode)
                .agreements(agreementFactory.create(subscriptionCode))
                .build();
    }

}
