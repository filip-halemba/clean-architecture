package io.halemba.invoice

import spock.lang.Specification

class AmountSpec extends Specification {

    def "amount can not be null"() {
        when:
        Amount.of(null)
        then:
        thrown IllegalArgumentException
    }

    def "amount can not be zero"() {
        when:
        Amount.of(BigDecimal.ZERO)
        then:
        thrown IllegalArgumentException
    }

    def "number can not be less than zero"() {
        when:
        Amount.of(BigDecimal.valueOf(-1))
        then:
        thrown IllegalArgumentException
    }

    def "create valid amount"() {
        when:
        Amount subject = Amount.of(BigDecimal.ONE)
        then:
        subject != null
        subject.getValue() == BigDecimal.ONE
    }

}
