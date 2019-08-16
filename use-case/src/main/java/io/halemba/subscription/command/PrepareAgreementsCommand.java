package io.halemba.subscription.command;

import io.halemba.subscription.CustomerId;
import io.halemba.subscription.ProcessId;
import lombok.Builder;
import lombok.NonNull;
import lombok.Value;

@Value
@Builder
public class PrepareAgreementsCommand {
    @NonNull
    ProcessId processId;
    @NonNull
    CustomerId customerId;
}
