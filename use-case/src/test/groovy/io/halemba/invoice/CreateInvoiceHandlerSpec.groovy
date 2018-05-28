package io.halemba.invoice

import spock.lang.Specification

import java.time.LocalDate

class CreateInvoiceHandlerSpec extends Specification {


    TestInvoiceRepository testInvoiceRepository = new TestInvoiceRepository()

    CreateInvoiceHandler subject = new CreateInvoiceHandler(testInvoiceRepository)

    def "cna not add incoive when exsit"() {
        given:
        invoiceIsInDb("1")
        when:
        subject.handle(new CreateInvoiceCommand("1", "9981590625", "8628823320", BigDecimal.ONE, LocalDate.now()))
        then:
        thrown BusinessOperationException
    }

    def "cna not add sdsd when exsit"() {
        when:
        subject.handle(new CreateInvoiceCommand("1", "9981590625", "8628823320", BigDecimal.ONE, LocalDate.now()))
        then:
        invoiceIsSaved("1")
    }

    private invoiceIsSaved(String number) {
        testInvoiceRepository.findByNumber(InvoiceNumber.of(number)).isPresent()
    }

    private invoiceIsInDb(String number) {
        testInvoiceRepository.saveSnapshot(new InvoiceSnapshot(
                UUID.randomUUID().toString(),
                number,
                "9981590625",
                "8628823320",
                BigDecimal.ONE,
                null,
                LocalDate.now())
        )
    }

}
