package io.halemba.subscription;

import lombok.*;

@Value
@Builder
@With(AccessLevel.PRIVATE)
class Agreement {
    @NonNull String code;
    @NonNull Long version;
    @NonNull Boolean isMandatory;

    String getLink() {
        return String.format("%s_%d", code, version);
    }

}
