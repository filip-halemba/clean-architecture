package io.halemba.subscription;

import io.halemba.subscription.answer.ChangeSubscriptionProcessStateAnswer;
import io.halemba.subscription.command.RequestChangeSubscriptionCommand;
import io.halemba.subscription.gateway.ChangeSubscriptionIdGenerator;
import io.halemba.subscription.gateway.CustomerAgreementsSender;
import io.halemba.subscription.gateway.CustomerAgreementsSender.MessageExternalCommand;
import io.halemba.subscription.gateway.SubscriptionSystem;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

import javax.transaction.Transactional;
import java.util.stream.Collectors;

@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
public class RequestChangeSubscriptionUseCase {

    private final SubscriptionSystem subscriptionSystem;
    private final CustomerAgreementsSender customerAgreementsSender;
    private final ChangeSubscriptionIdGenerator changeSubscriptionIdGenerator;
    private final ChangeSubscriptionProcessFactory changeSubscriptionProcessFactory;
    private final ChangeSubscriptionProcessRepository changeSubscriptionProcessRepository;

    @Transactional
    public ChangeSubscriptionProcessStateAnswer execute(RequestChangeSubscriptionCommand command) {
        checkIfOperationIsValid(command);
        ChangeSubscriptionProcess process = createProcess(command);
        customerAgreementsSender.send(prepareMessage(process));
        changeSubscriptionProcessRepository.save(process);
        return ChangeSubscriptionProcessMapper.toAnswer(process);
    }

    private void checkIfOperationIsValid(RequestChangeSubscriptionCommand command) {
        SubscriptionCode current = getCurrentSubscriptionCode(command.getCustomerId());
        if (!current.canBeChangedTo(command.getSubscriptionCode())) {
            throw new UnsupportedTransitionException(current, command.getSubscriptionCode());
        }
    }

    private SubscriptionCode getCurrentSubscriptionCode(CustomerId customerId) {
        return SubscriptionCode.of(subscriptionSystem.findForUser(customerId.getValueAsString()).getCode());
    }

    private ChangeSubscriptionProcess createProcess(RequestChangeSubscriptionCommand command) {
        return changeSubscriptionProcessFactory.create(
                generateProcessId(),
                command.getCustomerId(),
                command.getSubscriptionCode());
    }

    private ProcessId generateProcessId() {
        return ProcessId.of(changeSubscriptionIdGenerator.generate().toString());
    }

    private MessageExternalCommand prepareMessage(ChangeSubscriptionProcess process) {
        return MessageExternalCommand.builder()
                                     .customerId(process.getCustomerId().getValueAsString())
                                     .content("Some special message")
                                     .agreements(process.getAgreements().map(Agreement::getLink))
                                     .build();
    }

}
