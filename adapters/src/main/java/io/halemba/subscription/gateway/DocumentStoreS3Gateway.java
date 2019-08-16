package io.halemba.subscription.gateway;

import io.halemba.external.s3.S3Gateway;
import io.halemba.external.s3.S3Response;
import io.vavr.collection.Map;
import io.vavr.collection.Seq;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
@RequiredArgsConstructor
class DocumentStoreS3Gateway implements DocumentStore {

    private final S3Gateway s3Gateway;

    @Override
    public Seq<DocumentExternalResponse> search(Map<String, String> query) {
        return s3Gateway.search(query)
                        .map(toResponse());
    }

    private Function<S3Response, DocumentExternalResponse> toResponse() {
        return it -> DocumentExternalResponse.builder()
                                             .code(it.getCode())
                                             .isMandatory(it.getIsMandatory())
                                             .version(it.getVersion())
                                             .build();
    }

}
