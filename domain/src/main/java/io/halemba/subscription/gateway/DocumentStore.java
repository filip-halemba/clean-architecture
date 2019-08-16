package io.halemba.subscription.gateway;

import io.vavr.collection.Map;
import io.vavr.collection.Seq;
import lombok.Builder;
import lombok.Value;

import java.util.Collection;

public interface DocumentStore {

    @Value
    @Builder
    class DocumentExternalResponse {
        String code;
        Long version;
        Boolean isMandatory;
    }

    Seq<DocumentExternalResponse> search(Map<String, String> query);

}
