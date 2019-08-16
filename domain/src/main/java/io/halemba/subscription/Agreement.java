package io.halemba.subscription;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.NonNull;
import lombok.Value;
import lombok.experimental.Wither;

@Value
@Builder
@Wither(AccessLevel.PRIVATE)
class Agreement {
    @NonNull String code;
    @NonNull Long version;
    @NonNull Boolean isMandatory;

    String getLink() {
        return String.format("%s_%d", code, version);
    }

}
