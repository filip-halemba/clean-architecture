package io.halemba.external.smtp;

import io.vavr.collection.Set;
import lombok.Builder;
import lombok.Value;

import java.util.Collection;

@Value
@Builder
public class MailMessage {
    String content;
    String receiver;
    Set<String> attachments;
}
