package io.halemba.invoice;

import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.UUID;

@ToString
@EqualsAndHashCode
public class InvoiceId {

    private final UUID value;

    private InvoiceId(UUID value) {
        this.value = value;
    }

    public static InvoiceId of(UUID value) {
        if (value == null) {
            throw new IllegalArgumentException("uuid can not be null");
        }
        return new InvoiceId(value);
    }

    static InvoiceId of(String value) {
        if (value == null || value.isEmpty() || !value.contains("-")) {
            throw new IllegalArgumentException("uuid can not be null or invalid uuid");
        }
        return new InvoiceId(UUID.fromString(value));
    }

    String asString() {
        return value.toString();
    }
}
