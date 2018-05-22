package pl.waw.krakus.test.parking.model

import spock.lang.Specification

class ParkingMeterTest extends Specification {
    def "should start parkingMeter and report started Parking Sessions"() {
        given:
        Vehicle vehicle = new Vehicle("WF12345")
        ParkSessionsRepository repository = new ParkSessionsRepositorImpl()
        ParkingMeterInterface parkingMeter = new ParkingMeter(repository)

        when:
        parkingMeter.startParkSessionFor(vehicle,DriverType.REGULAR)

        then:
        parkingMeter.isParkSessionStartedFor(vehicle)

        when:
        Vehicle anotherVehicle =  new Vehicle("WWL99112")
        parkingMeter.startParkSessionFor(anotherVehicle,DriverType.VIP)

        then:
        parkingMeter.isParkSessionStartedFor(anotherVehicle)

        when:
        parkingMeter.stopParkSessionFor(anotherVehicle)

        then:
        parkingMeter.isParkSessionStartedFor(anotherVehicle) == false
    }

    def "should stop parkingMeter"() {
        given:
        Vehicle vehicle = new Vehicle("AB12346")
        ParkSessionsRepository repository = new ParkSessionsRepositorImpl()
        ParkingMeterInterface parkingMeter = new ParkingMeter(repository)

        when:
        parkingMeter.startParkSessionFor(vehicle,DriverType.REGULAR)

        then:
        parkingMeter.isParkSessionStartedFor(vehicle)

        when:
        parkingMeter.stopParkSessionFor(vehicle)

        then:
        parkingMeter.isParkSessionStartedFor(vehicle) == false

    }

    def "should not start another session for same Vehicle"() {
        given:
        Vehicle vehicle = new Vehicle("WF12345")
        ParkSessionsRepository repository = new ParkSessionsRepositorImpl()
        ParkingMeterInterface parkingMeter = new ParkingMeter(repository)

        when: "start session first time"
        def firstParkSession = parkingMeter.startParkSessionFor(vehicle,DriverType.REGULAR)

        then: "parkingMeter return started session"
        firstParkSession.isNotFinished()
        parkingMeter.isParkSessionStartedFor(vehicle)

        when: "start session second time until previous is stopped"
        def duplicatedSession = parkingMeter.startParkSessionFor(vehicle,DriverType.REGULAR)

        then: "return empty finished session"
        duplicatedSession.isNotFinished() == false

        when: "start VIP session third time until previous is stopped"
        def duplicatedVipSession = parkingMeter.startParkSessionFor(vehicle,DriverType.VIP)

        then: "return empty finished session"
        duplicatedVipSession.isNotFinished() == false

        and: "old session is still active"
        parkingMeter.isParkSessionStartedFor(vehicle)
    }
}
