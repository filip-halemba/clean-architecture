package io.halemba.subscription.gateway;

import io.halemba.external.smtp.MailMessage;
import io.halemba.external.smtp.MailSender;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
class CustomerAgreementsMailSender implements CustomerAgreementsSender {

    private final MailSender mailSender;

    @Override
    public void send(MessageExternalCommand command) {
        mailSender.send(MailMessage.builder()
                                   .receiver(command.getCustomerId())
                                   .content(command.getContent())
                                   .attachments(command.getAgreements())
                                   .build());
    }

}
