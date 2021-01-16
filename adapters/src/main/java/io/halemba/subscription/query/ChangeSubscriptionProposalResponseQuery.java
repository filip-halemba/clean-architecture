package io.halemba.subscription.query;

import io.vavr.collection.List;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class ChangeSubscriptionProposalResponseQuery {
    List<String> items;
}
