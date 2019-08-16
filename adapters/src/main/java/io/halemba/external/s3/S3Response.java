package io.halemba.external.s3;

import lombok.Builder;
import lombok.Value;

import java.util.Collection;

@Value
@Builder
public class S3Response {
    String code;
    Long version;
    Boolean isMandatory;
}
