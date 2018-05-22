package pl.waw.krakus.test.parking.model;

import java.util.ArrayList;
import java.util.List;

public class ParkSessionsRepositorImpl implements ParkSessionsRepository {

  private List<ParkSessionInterface> parkSessionList = new ArrayList<>();

  @Override
  public boolean add(ParkSessionInterface parkSession) {
    return parkSessionList.add(parkSession);
  }

  @Override
  public boolean containsStartedSession(ParkSessionInterface parkSession) {
    return parkSessionList.contains(parkSession) && parkSession.isNotFinished();
  }

  @Override
  public boolean containsStartedSession(Vehicle vehicle) {
    ParkSessionInterface parkSession = getSessionFor(vehicle);
    return parkSession.isNotFinished();
  }

  @Override
  public boolean setSessionAsStoppedFor(Vehicle vehicle) {
    ParkSessionInterface parkSession = getSessionFor(vehicle);
    return parkSession.stopParkSession();
  }

  @Override
  public ParkSessionInterface getSessionFor(Vehicle vehicle) {
    ParkSessionInterface result = new EmptyParkSession();
    for (ParkSessionInterface parkSession : parkSessionList) {
      if (parkSession.getVehicle().equals(vehicle)) {
        return parkSession;
      }
    }

    return result;
  }
}
