package io.halemba.invoice;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/invoices")
@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
class InvoiceController {

    private final CreateInvoiceHandler createInvoiceHandler;
    private final InvoiceProjectionRepository invoiceProjectionRepository;

    @PostMapping
    ResponseEntity<Void> createInvoice(@RequestBody CreateInvoiceBody createInvoiceBody) {
        createInvoiceHandler.handle(convertToCreateInvoiceCommand(createInvoiceBody));
        return ResponseEntity.ok().build();
    }

    @GetMapping
    ResponseEntity<String> getInvoices() {
        return ResponseEntity.ok("LIST");
    }

    private CreateInvoiceCommand convertToCreateInvoiceCommand(CreateInvoiceBody createInvoiceBody) {
        return new CreateInvoiceCommand(
                createInvoiceBody.getNumber(),
                createInvoiceBody.getBuyerNip(),
                createInvoiceBody.getSellerNip(),
                createInvoiceBody.getAmount(),
                LocalDate.now()
        );
    }

}
