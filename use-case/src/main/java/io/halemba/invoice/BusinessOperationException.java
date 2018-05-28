package io.halemba.invoice;

public class BusinessOperationException extends RuntimeException {
    BusinessOperationException(String message) {
        super(message);
    }
}
