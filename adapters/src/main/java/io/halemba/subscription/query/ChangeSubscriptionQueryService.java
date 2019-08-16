package io.halemba.subscription.query;

import io.vavr.collection.List;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ChangeSubscriptionQueryService {

    public ChangeSubscriptionProposalResponseQuery getProposal(String subscriptionCode) {
        return ChangeSubscriptionProposalResponseQuery.builder()
                                                      .items(List.of("SUPER_PROMOTION", "SUPER_NEW_FEATURE"))
                                                      .build();
    }

    public Optional<ChangeSubscriptionStateQuery> query(ChangeSubscriptionStateQuery build) {
        return null;
    }
}
