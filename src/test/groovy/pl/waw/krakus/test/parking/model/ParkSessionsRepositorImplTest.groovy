package pl.waw.krakus.test.parking.model

import spock.lang.Specification

class ParkSessionsRepositorImplTest extends Specification {
    def "should instantiate ParkSessionsRepositorImpl"() {
        when:
        new ParkSessionsRepositorImpl()

        then:
        noExceptionThrown()
    }

    def "should add ParkSession"() {
        when:
        Vehicle vehicle = new Vehicle("SK998877")
        ParkSessionInterface parkSession = new ParkSession(vehicle,DriverType.REGULAR)
        ParkSessionsRepositorImpl sessionsRepository = new ParkSessionsRepositorImpl()

        then:
        sessionsRepository.parkSessionList.isEmpty()

        when:
        sessionsRepository.add(parkSession)

        then:
        sessionsRepository.parkSessionList.contains(parkSession)
    }

    def "should report if contains started"() {
        given:
        Vehicle vehicle = new Vehicle("SK998877")
        ParkSessionInterface firstSession = new ParkSession(vehicle,DriverType.VIP)
        ParkSessionsRepositorImpl sessionsRepository = new ParkSessionsRepositorImpl()

        when: "add started session"
        sessionsRepository.add(firstSession)

        then:
        sessionsRepository.containsStartedSession(firstSession)

        when: "stop added previously"
        firstSession.stopParkSession()

        then:
        sessionsRepository.containsStartedSession(firstSession) == false

        when: "added new stopped session"
        def stoppedSession = new ParkSession(new Vehicle("AB1234"),DriverType.REGULAR)
        stoppedSession.stopParkSession()
        sessionsRepository.containsStartedSession(stoppedSession)

        then:
        sessionsRepository.containsStartedSession(stoppedSession) == false


        when: "session is not in repository"
        def notAddedSession = new ParkSession(new Vehicle("CD998877"),DriverType.VIP)

        then:
        sessionsRepository.containsStartedSession(notAddedSession) == false


    }


    def "should return ParkSession"() {
        given:
        Vehicle vehicle = new Vehicle("SK998877")
        ParkSessionInterface parkSession = new ParkSession(vehicle,DriverType.REGULAR)
        ParkSessionsRepositorImpl sessionsRepository = new ParkSessionsRepositorImpl()

        when:
        sessionsRepository.add(parkSession)

        then:
        sessionsRepository.getSessionFor(vehicle).getVehicle() == vehicle

        and:
        sessionsRepository.containsStartedSession(parkSession)

        and:
        sessionsRepository.containsStartedSession(vehicle)
    }

}
