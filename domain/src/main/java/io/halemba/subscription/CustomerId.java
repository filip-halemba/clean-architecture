package io.halemba.subscription;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import lombok.ToString;

import java.util.UUID;

@Getter
@ToString
@EqualsAndHashCode
public class CustomerId {

    private final UUID value;

    public static CustomerId of(@NonNull String value) {
        return new CustomerId(UUID.fromString(value));
    }

    private CustomerId(UUID value) {
        this.value = value;
    }

    String getValueAsString() {
        return value.toString();
    }

    UUID getValue() {
        return value;
    }

}
