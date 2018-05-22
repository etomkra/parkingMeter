package pl.waw.krakus.test.parking.model;

public class ParkingMeter implements ParkingMeterInterface {

  private ParkSessionsRepository parkSessionsRepository;

  public ParkingMeter() {
    this.parkSessionsRepository = new ParkSessionsRepositorImpl();
  }

  public ParkingMeter(ParkSessionsRepository parkSessionsRepository) {
    this.parkSessionsRepository = parkSessionsRepository;
  }

  @Override
  public ParkSessionInterface startParkSessionFor(Vehicle vehicle, DriverType driverType) {
    ParkSessionInterface newSession;

    if (isParkSessionStartedFor(vehicle)) {
      return new EmptyParkSession();

    } else {
      newSession = new ParkSession(vehicle, driverType);

      if (parkSessionsRepository.add(newSession)) {
        return newSession;

      } else {
        return new EmptyParkSession();
      }
    }
  }

  @Override
  public boolean isParkSessionStartedFor(Vehicle vehicle) {
    return parkSessionsRepository.containsStartedSession(vehicle);
  }

  @Override
  public ParkSessionInterface stopParkSessionFor(Vehicle vehicle) {
    if (parkSessionsRepository.setSessionAsStoppedFor(vehicle)) {
      return parkSessionsRepository.getSessionFor(vehicle);
    } else {
      return new EmptyParkSession();
    }
  }

  @Override
  public ParkSessionInterface getParkSessionFor(Vehicle vehicle) {
    return parkSessionsRepository.getSessionFor(vehicle);
  }
}
