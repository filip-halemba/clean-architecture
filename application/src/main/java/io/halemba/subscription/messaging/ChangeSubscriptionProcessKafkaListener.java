package io.halemba.subscription.messaging;

import io.halemba.subscription.ConfirmChangeSubscriptionUseCase;
import io.halemba.subscription.CustomerId;
import io.halemba.subscription.ProcessId;
import io.halemba.subscription.command.ConfirmChangeSubscriptionCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ChangeSubscriptionProcessKafkaListener {

    private final ConfirmChangeSubscriptionUseCase confirmChangeSubscriptionUseCase;

    //FIXME Add kafka annotations and avro or json
    public void onEvent(String processId, String customerId) {
        confirmChangeSubscriptionUseCase.execute(ConfirmChangeSubscriptionCommand.builder()
                                                                                 .processId(ProcessId.of(processId))
                                                                                 .customerId(CustomerId.of(customerId))
                                                                                 .build());
    }

}
