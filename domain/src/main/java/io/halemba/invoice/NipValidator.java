package io.halemba.invoice;

class NipValidator {

    static boolean isNotValid(String value) {
        return !(value.length() == 10);
    }

}
