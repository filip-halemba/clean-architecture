package io.halemba.invoice

import spock.lang.Specification

class InvoiceIdSpec extends Specification {

    def "should throw exception when string value is null"() {
        when:
            InvoiceId.of( (String) null)
        then:
            thrown IllegalArgumentException
    }

    def "should throw exception when string value is empty"() {
        when:
            InvoiceId.of("")
        then:
            thrown IllegalArgumentException
    }

    def "should throw exception when string value does not contain '-'"() {
        given:
            UUID uuid = UUID.randomUUID()
            String uuidAsString = uuid.toString()
            String uuidAsStringWithoutDashes = uuidAsString.replace("-", "")
        when:
            InvoiceId.of(uuidAsStringWithoutDashes)
        then:
            thrown IllegalArgumentException
    }

    def "should throw exception when uuid value is null"() {
        when:
            InvoiceId.of( (UUID) null)
        then:
            thrown IllegalArgumentException
    }

    def "should create object from valid uuid"() {
        given:
            UUID uuid = UUID.randomUUID()
        when:
            InvoiceId subject = InvoiceId.of(uuid)
        then:
            subject != null
            subject.asString() == uuid.toString()
    }

    def "should create object from valid uuid as string"() {
        given:
            String uuidAsString = UUID.randomUUID().toString()
        when:
            InvoiceId subject = InvoiceId.of(uuidAsString)
        then:
            subject != null
            subject.asString() == uuidAsString
    }

}
