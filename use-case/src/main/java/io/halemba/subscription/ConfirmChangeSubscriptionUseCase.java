package io.halemba.subscription;

import io.halemba.subscription.ChangeSubscriptionProcess.Status;
import io.halemba.subscription.gateway.SubscriptionSystem;
import io.halemba.subscription.gateway.SubscriptionSystem.SubscriptionSystemStatus;
import io.halemba.subscription.command.ConfirmChangeSubscriptionCommand;
import lombok.RequiredArgsConstructor;

import javax.transaction.Transactional;

import java.time.Instant;

import static io.vavr.API.*;

@RequiredArgsConstructor
public class ConfirmChangeSubscriptionUseCase {

    private final SubscriptionSystem subscriptionSystem;
    private final SubscriptionInformationUpdater subscriptionInformationUpdater;
    private final ChangeSubscriptionProcessRepository changeSubscriptionProcessRepository;

    @Transactional
    public void execute(final ConfirmChangeSubscriptionCommand command) {
        ChangeSubscriptionProcess process = findProcess(command.getProcessId(), command.getCustomerId());
        if (Status.REJECTED.equals(process.getStatus())) {
            confirm(process);
        } else {
            throw new IllegalProcessStateException(process);
        }
    }

    private void confirm(final ChangeSubscriptionProcess process) {
        SubscriptionSystemStatus subscriptionSystemStatus = getStatusFromSubscriptionSystem(process.getProcessId());
        if (SubscriptionSystemStatus.OK.equals(subscriptionSystemStatus)) {
            subscriptionInformationUpdater.changeFor(process);
        }
        changeSubscriptionProcessRepository.save(updateProcessStatus(process, subscriptionSystemStatus));
    }

    private ChangeSubscriptionProcess updateProcessStatus(final ChangeSubscriptionProcess process, final SubscriptionSystemStatus subscriptionSystemStatus) {
        return Match(subscriptionSystemStatus).of(
                Case($(SubscriptionSystemStatus.OK), process.confirmed(Instant.now())),
                Case($(SubscriptionSystemStatus.NOT_OK), process.rejected(Instant.now()))
        );
    }

    private SubscriptionSystemStatus getStatusFromSubscriptionSystem(final ProcessId processId) {
        return subscriptionSystem.check(processId.getValueAsString());
    }

    private ChangeSubscriptionProcess findProcess(final ProcessId processId, final CustomerId customerId) {
        return changeSubscriptionProcessRepository.find(processId)
                .filter(it -> it.belongsTo(customerId))
                .orElseThrow(IllegalStateException::new);
    }

}
