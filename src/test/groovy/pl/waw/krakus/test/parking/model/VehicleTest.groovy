package pl.waw.krakus.test.parking.model

import spock.lang.Specification
import spock.lang.Unroll

class VehicleTest extends Specification {

    def "should instantiate new Vehicle"() {
        when:
        def vehicle = new Vehicle("ABC1234")

        then:
        noExceptionThrown()

        and:
        vehicle.getRegistrationNumber() == "ABC1234"
    }

    def "should create same objects using same registration number"() {
        when:
        def vehicle1 = new Vehicle("ABC123")
        def vehicle2 = new Vehicle("ABC123")

        then:
        vehicle1 == vehicle2
    }

    @Unroll
    def "should thrown an exception when registration number is (#testDesc)"() {
        when:
        new Vehicle(regNumber)

        then:
        thrown(Exception)

        where:
        testDesc | regNumber
        "null"   | null
    }
}
