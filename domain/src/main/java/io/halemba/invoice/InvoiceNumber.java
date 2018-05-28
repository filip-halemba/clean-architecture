package io.halemba.invoice;

import lombok.EqualsAndHashCode;
import lombok.ToString;

@ToString
@EqualsAndHashCode
class InvoiceNumber {

    private final String value;

    private InvoiceNumber(String value) {
        if (value == null || value.isEmpty() || value.length() > 20) {
            throw new IllegalArgumentException("value can not be nor null, empty or longer than 20");
        }
        this.value = value;
    }

    static InvoiceNumber of(String value) {
        return new InvoiceNumber(value);
    }

    String getValue() {
        return value;
    }

}
