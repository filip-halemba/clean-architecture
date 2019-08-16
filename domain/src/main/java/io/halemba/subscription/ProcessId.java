package io.halemba.subscription;


import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import lombok.ToString;

import java.util.UUID;

@Getter
@ToString
@EqualsAndHashCode
public class ProcessId {

    private final UUID value;

    public static ProcessId of(@NonNull String value) {
        return new ProcessId(UUID.fromString(value));
    }

    private ProcessId(UUID value) {
        this.value = value;
    }

    String getValueAsString() {
        return value.toString();
    }

    UUID getValue() {
        return value;
    }

}
