package io.halemba.subscription;


import lombok.NonNull;
import lombok.ToString;

@ToString
public class SubscriptionCode {

    enum Value {
        STANDARD,
        PREMIUM
    }

    private final Value value;

    public static SubscriptionCode of(@NonNull String value) {
        try {
            return new SubscriptionCode(Value.valueOf(value));
        } catch (IllegalArgumentException ex) {
            throw new IllegalSubscriptionCodeException(value);
        }
    }

    private SubscriptionCode(Value value) {
        this.value = value;
    }

    String getValueAsString() {
        return value.toString();
    }

    Value getValue() {
        return value;
    }

    boolean canBeChangedTo(SubscriptionCode subscriptionCode) {
        if (value.equals(Value.STANDARD) && subscriptionCode.getValue().equals(Value.PREMIUM)) {
            return true;
        } else {
            return value.equals(Value.PREMIUM) && subscriptionCode.getValue().equals(Value.STANDARD);
        }
    }

}
