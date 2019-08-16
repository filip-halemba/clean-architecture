package io.halemba.external.s3;

import io.vavr.collection.List;
import io.vavr.collection.Map;
import io.vavr.collection.Seq;
import org.springframework.stereotype.Service;

@Service
public class S3Gateway {

    public Seq<S3Response> search(Map<String, String> prepareQuery) {
        return List.of(
                S3Response.builder()
                          .code("1")
                          .isMandatory(true)
                          .version(1L)
                          .build(),
                S3Response.builder()
                          .code("2")
                          .isMandatory(true)
                          .version(1L)
                          .build(),
                S3Response.builder()
                          .code("3")
                          .isMandatory(true)
                          .version(1L)
                          .build(),
                S3Response.builder()
                          .code("4")
                          .isMandatory(false)
                          .version(2L)
                          .build()
        );
    }

}
