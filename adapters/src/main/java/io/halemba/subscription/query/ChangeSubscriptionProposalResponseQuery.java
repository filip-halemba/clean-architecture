package io.halemba.subscription.query;

import io.vavr.collection.Seq;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class ChangeSubscriptionProposalResponseQuery {
    Seq<String> items;
}
