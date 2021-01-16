package io.halemba.subscription.query;

import io.halemba.external.subscription.SubscriptionSystemRestFacade;
import io.vavr.collection.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ChangeSubscriptionQueryService {

    private final SubscriptionSystemRestFacade subscriptionSystemRestFacade;

    public ChangeSubscriptionProposalResponseQuery getProposal(String subscriptionCode) {
        return ChangeSubscriptionProposalResponseQuery.builder()
                                                      .items(List.of(
                                                              subscriptionSystemRestFacade.generateProposal(subscriptionCode)
                                                             )
                                                      )
                                                      .build();
    }

    public Optional<ChangeSubscriptionStateQuery> query(ChangeSubscriptionStateQuery build) {
        return null;
    }
}
