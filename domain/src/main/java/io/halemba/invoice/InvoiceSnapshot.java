package io.halemba.invoice;

import lombok.Value;

import java.math.BigDecimal;
import java.time.LocalDate;

@Value
class InvoiceSnapshot {
    String id;
    String number;
    String buyerNip;
    String sellerNip;
    BigDecimal amount;
    LocalDate endDate;
    LocalDate billingDate;
}
