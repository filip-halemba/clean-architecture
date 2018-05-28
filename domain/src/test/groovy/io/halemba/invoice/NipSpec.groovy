package io.halemba.invoice

import spock.lang.Specification

class NipSpec extends Specification {

    def "nip can not be null"() {
        when:
        Nip.of(null)
        then:
        thrown IllegalArgumentException
    }

    def "number can not be empty"() {
        when:
        Nip.of("")
        then:
        thrown IllegalArgumentException
    }

    def "nip should have 10 characters"() {
        when:
        Nip.of("889371715")
        then:
        thrown IllegalArgumentException
    }

    def "create valid nip number"() {
        when:
        Nip subject = Nip.of("8893717155")
        then:
        subject != null
        subject.getValue() == "8893717155"
    }

}
