package io.halemba.invoice;

import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.math.BigDecimal;

@ToString
@EqualsAndHashCode //maybe write custom one with compareTo method from BigDecimal?
class Amount {

    private final BigDecimal value;

    private Amount(BigDecimal value) {
        if (value == null) {
            throw new IllegalArgumentException("value can not be nor null or empty");
        }
        if (value.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("value can not be less or equal 0");
        }
        this.value = value;
    }

    static Amount of(BigDecimal value) {
        return new Amount(value);
    }

    BigDecimal getValue() {
        return value;
    }

}
