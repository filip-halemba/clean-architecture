package io.halemba.invoice;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
class Invoice {

    private LocalDate endDate;

    private final Nip buyer;
    private final Nip seller;
    private final Amount amount;
    private final InvoiceId invoiceId;
    private final LocalDate billingDate;
    private final InvoiceNumber invoiceNumber;

    static Invoice create(InvoiceId invoiceId, InvoiceNumber invoiceNumber, Amount amount, Nip buyer, Nip seller, LocalDate billingDate) {
        return new Invoice(buyer, seller, amount, invoiceId, billingDate, invoiceNumber);
    }

    static Invoice restore(InvoiceSnapshot invoiceSnapshot) {
        return new Invoice(
                invoiceSnapshot.getEndDate(),
                Nip.of(invoiceSnapshot.getBuyerNip()),
                Nip.of(invoiceSnapshot.getBuyerNip()),
                Amount.of(invoiceSnapshot.getAmount()),
                InvoiceId.of(invoiceSnapshot.getId()),
                invoiceSnapshot.getBillingDate(),
                InvoiceNumber.of(invoiceSnapshot.getNumber())
        );
    }

    InvoiceSnapshot getSnapshot() {
        return new InvoiceSnapshot(
                invoiceId.asString(),
                invoiceNumber.getValue(),
                buyer.getValue(),
                seller.getValue(),
                amount.getValue(),
                endDate,
                billingDate
        );
    }

}
