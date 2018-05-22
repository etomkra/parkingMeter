package pl.waw.krakus.test.parking.model;

public class EmptyParkSession implements ParkSessionInterface {
  @Override
  public boolean isNotFinished() {
    return false;
  }

  @Override
  public boolean stopParkSession() {
    return false;
  }

  @Override
  public Vehicle getVehicle() {
    return new EmptyVehicle();
  }

  @Override
  public DriverType getDriverType() {
    return DriverType.NULL;
  }

  @Override
  public long getTotalTime() {
    return 0;
  }
}
