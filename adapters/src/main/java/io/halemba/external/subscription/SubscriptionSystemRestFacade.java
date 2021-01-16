package io.halemba.external.subscription;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class SubscriptionSystemRestFacade {


    private final RestTemplate restTemplate;
    private final SubscriptionSystemConfiguration subscriptionSystemConfiguration;

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

    public String generateProposal(String subscriptionCode) {
        return String.format(
                "%s_%s",
                subscriptionCode,
                restTemplate.getForObject(subscriptionSystemConfiguration.getUrl() + "/generate-proposal", String.class)
        );
    }
}
