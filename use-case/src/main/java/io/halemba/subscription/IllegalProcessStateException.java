package io.halemba.subscription;

class IllegalProcessStateException extends RuntimeException {

    IllegalProcessStateException(ChangeSubscriptionProcess process) {
        super(String.format("Process(%s) is in illegal state to perform this operation.",
                process.getProcessId().getValueAsString()));
    }
}
