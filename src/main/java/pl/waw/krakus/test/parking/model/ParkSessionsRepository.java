package pl.waw.krakus.test.parking.model;

public interface ParkSessionsRepository {
  boolean add(ParkSessionInterface parkSession);

  boolean containsStartedSession(ParkSessionInterface parkSession);

  boolean containsStartedSession(Vehicle vehicle);

  boolean setSessionAsStoppedFor(Vehicle vehicle);

  ParkSessionInterface getSessionFor(Vehicle vehicle);
}
