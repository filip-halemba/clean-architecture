package io.halemba.subscription;

import io.halemba.subscription.gateway.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class ChangeSubscriptionProcessConfiguration {

    @Bean
    RequestChangeSubscriptionUseCase requestChangeSubscriptionUseCase(
            SubscriptionSystem subscriptionSystem,
            CustomerAgreementsSender customerAgreementsSender,
            ChangeSubscriptionIdGenerator changeSubscriptionIdGenerator,
            ChangeSubscriptionProcessFactory changeSubscriptionProcessFactory,
            ChangeSubscriptionProcessRepository changeSubscriptionProcessRepository) {
        return new RequestChangeSubscriptionUseCase(
                subscriptionSystem,
                customerAgreementsSender,
                changeSubscriptionIdGenerator,
                changeSubscriptionProcessFactory,
                changeSubscriptionProcessRepository);
    }

    @Bean
    ConfirmChangeSubscriptionUseCase confirmChangeSubscriptionUseCase(
            SubscriptionSystem subscriptionSystem,
            PaymentSystem paymentSystem,
            ChangeSubscriptionProcessRepository changeSubscriptionProcessRepository) {
        return new ConfirmChangeSubscriptionUseCase(
                subscriptionSystem,
                subscriptionInformationUpdater(subscriptionSystem, paymentSystem),
                changeSubscriptionProcessRepository);
    }
    @Bean
    SubscriptionInformationUpdater subscriptionInformationUpdater(SubscriptionSystem subscriptionSystem,
                                                                  PaymentSystem paymentSystem) {
        return new SubscriptionInformationUpdater(subscriptionSystem, paymentSystem);
    }


    @Bean
    ChangeSubscriptionProcessFactory changeSubscriptionProcessFactory(DocumentStore documentStore) {
        return new ChangeSubscriptionProcessFactory(new AgreementFactory(documentStore));
    }

    @Bean
    ChangeSubscriptionProcessRepository changeSubscriptionProcessRepository() {
        return new ChangeSubscriptionProcessRepositoryInMemory();
    }

}
