package io.halemba.invoice

import spock.lang.Specification

class InvoiceNumberSpec extends Specification {

    def "number can not be null"() {
        when:
            InvoiceNumber.of(null)
        then:
            thrown IllegalArgumentException
    }

    def "number can not be empty"() {
        when:
        InvoiceNumber.of("")
        then:
        thrown IllegalArgumentException
    }

    def "number can not be longer than 20 characters"() {
        when:
        InvoiceNumber.of("123456789012345678901")
        then:
        thrown IllegalArgumentException
    }

    def "create valid number"() {
        when:
        InvoiceNumber subject = InvoiceNumber.of("2017/11/1/FV1")
        then:
        subject != null
        subject.getValue() == "2017/11/1/FV1"
    }

}
