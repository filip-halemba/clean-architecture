package io.halemba.subscription;

class IllegalSubscriptionCodeException extends RuntimeException {

    IllegalSubscriptionCodeException(String value) {
        super("Value = " + value + " is illegal");
    }

}
