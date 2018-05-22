package pl.waw.krakus.test.parking.model;

public interface ParkingMeterInterface {

  ParkSessionInterface startParkSessionFor(Vehicle vehicle, DriverType driverType);

  boolean isParkSessionStartedFor(Vehicle vehicle);

  ParkSessionInterface stopParkSessionFor(Vehicle vehicle);

  ParkSessionInterface getParkSessionFor(Vehicle vehicle);
}
