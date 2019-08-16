package io.halemba.subscription;

class UnsupportedTransitionException extends RuntimeException {

    UnsupportedTransitionException(SubscriptionCode current, SubscriptionCode requested) {
        super(String.format("Transition from %s to %s is illegal.", current, requested));
    }
}
