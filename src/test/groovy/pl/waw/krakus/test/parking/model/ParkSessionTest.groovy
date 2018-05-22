package pl.waw.krakus.test.parking.model

import spock.lang.Specification
import spock.lang.Unroll

import java.time.LocalDateTime

class ParkSessionTest extends Specification {

    @Unroll
    def "should instantiate ParkSession for #driverType"() {
        given:
        def vehicle = new Vehicle("ABC123")

        when:
        new ParkSession(vehicle, driverType)

        then:
        noExceptionThrown()

        where:
        driverType         || _
        DriverType.REGULAR || _
        DriverType.VIP     || _
    }

    @Unroll
    def "should start every new ParkSession for #driverType"() {
        given:
        def vehicle = new Vehicle("ABC123")

        when:
        def session = new ParkSession(vehicle,driverType)

        then:
        session.startDateTime != null
        session.vehicle == vehicle

        where:
        driverType         || _
        DriverType.REGULAR || _
        DriverType.VIP     || _
    }

    @Unroll
    def "should assign stopDateTime when stopped for #driverType"() {
        given:
        def vehicle = new Vehicle("ABC123")

        when:
        def session = new ParkSession(vehicle, driverType)

        then:
        session.startDateTime != null
        session.stopDateTime == null

        when:
        Thread.sleep(1)
        session.stopParkSession()

        then:
        session.stopDateTime != null
        session.stopDateTime.isAfter(session.startDateTime)

        where:
        driverType         || _
        DriverType.REGULAR || _
        DriverType.VIP     || _
    }

    def "should return vehicle"() {
        def vehicle = new Vehicle("ABC123")
        def session = new ParkSession(vehicle, DriverType.REGULAR)

        when:
        def result = session.getVehicle()

        then:
        result == vehicle
        result.getRegistrationNumber() == "ABC123"
    }

    def "should report isNotFinished"() {
        given:
        def vehicle = new Vehicle("ABC123")
        def session = new ParkSession(vehicle, DriverType.VIP)

        expect:
        session.isNotFinished()

        when:
        session.stopParkSession()

        then:
        session.isNotFinished() == false
    }

    def "should return session time in started hours"() {
        given:
        def vehicle = new Vehicle("ABC123")

        when: "session is not stopped yet"
        def session = new ParkSession(vehicle, DriverType.VIP)

        then: "return 0 hours"
        assert session.notFinished
        session.getTotalTime() == 0

        when: "session is stopped after 0.5 sec"
        Thread.sleep(500)
        session.stopParkSession()

        then: "1st hour is started and return 1 hour"
        session.getTotalTime() == 1

        when: "manually modify stopDateTime and add 1 hour"
        session.stopDateTime = session.startDateTime.plusHours(1)

        then: "2nd hour is started"
        session.getTotalTime() == 2

        when: "manually modify stopDateTime and add 9 hours"
        session.stopDateTime = session.startDateTime.plusHours(9)

        then: "10th hour is started"
        session.getTotalTime() == 10

        when: "startDateTime == stopDateTime"
        session.stopDateTime = session.startDateTime

        then: "return 0"
        session.getTotalTime() == 0
    }
}
