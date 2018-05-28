package io.halemba.invoice;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class InvoiceConfiguration {

    @Bean
    InMemoryInvoiceRepository inMemoryInvoiceRepository() {
        //FIXME Change to JPA?
        return new InMemoryInvoiceRepository();
    }

    @Bean
    InvoiceController invoiceController(InMemoryInvoiceRepository inMemoryInvoiceRepository) {
        CreateInvoiceHandler createInvoiceHandler = new CreateInvoiceHandler(inMemoryInvoiceRepository);
        return new InvoiceController(createInvoiceHandler, inMemoryInvoiceRepository);
    }

}
