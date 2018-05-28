package io.halemba.invoice;

import lombok.EqualsAndHashCode;
import lombok.ToString;

@ToString
@EqualsAndHashCode
class Nip {

    private final String value;

    private Nip(String value) {
        if (value == null || value.isEmpty()) {
            throw new IllegalArgumentException("value can not be nor null or empty");
        }
        if (NipValidator.isNotValid(value)) {
            throw new IllegalArgumentException("value is not valid nip format");
        }
        this.value = value;
    }

    static Nip of(String value) {
        return new Nip(value);
    }

    String getValue() {
        return value;
    }

}
