package io.halemba.invoice;

import java.util.Optional;

public interface InvoiceRepository {
    void save(Invoice invoice);
    Invoice findById(InvoiceId invoiceId);
    Optional<Invoice> findByNumber(InvoiceNumber number);
}
