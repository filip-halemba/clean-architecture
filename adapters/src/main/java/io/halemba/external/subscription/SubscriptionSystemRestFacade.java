package io.halemba.external.subscription;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Slf4j
@Component
public class SubscriptionSystemRestFacade {

    public String checkCustomerStatus(String customerId) {
        if (customerId.contains("1")) {
            return "OK";
        } else {
            return "NOT_OK";
        }
    }

    public String changeSubscription(ChangeCustomerSubscriptionJson changeCustomerSubscriptionJson) {
        return UUID.randomUUID().toString();
    }

    public String findSubscriptionFor(String customerId) {
        if (customerId.contains("1")) {
            return "STANDARD";
        } else {
            return "PREMIUM";
        }
    }
}
