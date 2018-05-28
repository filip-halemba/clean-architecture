package io.halemba.invoice;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

import javax.transaction.Transactional;
import java.util.UUID;

@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
public class CreateInvoiceHandler {

    private final InvoiceRepository invoiceRepository;

    @Transactional
    void handle(CreateInvoiceCommand command) {
        if (invoiceWithSameNumberExists(command)) {
            throw new BusinessOperationException("Invoice with same number already exists!");
        } else {
            Invoice invoice = createInvoice(command);
            invoiceRepository.save(invoice);
        }
    }

    private boolean invoiceWithSameNumberExists(CreateInvoiceCommand command) {
        InvoiceNumber invoiceNumber = InvoiceNumber.of(command.getNumber());
        return invoiceRepository.findByNumber(invoiceNumber) != null;
    }

    private Invoice createInvoice(CreateInvoiceCommand command) {
        return Invoice.create(
                InvoiceId.of(UUID.randomUUID()),
                InvoiceNumber.of(command.getNumber()),
                Amount.of(command.getAmount()),
                Nip.of(command.getBuyerNip()),
                Nip.of(command.getSellerNip()),
                command.getBillingDate()
        );
    }
}
