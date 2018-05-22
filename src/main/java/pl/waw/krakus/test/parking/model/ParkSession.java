package pl.waw.krakus.test.parking.model;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class ParkSession implements ParkSessionInterface {
  private LocalDateTime startDateTime;
  private LocalDateTime stopDateTime;
  private Vehicle vehicle;
  private DriverType driverType;

  public ParkSession(Vehicle vehicle, DriverType driverType) {
    this.startDateTime = LocalDateTime.now();
    this.vehicle = vehicle;
    this.driverType = driverType;
  }

  @Override
  public boolean isNotFinished() {
    return stopDateTime == null;
  }

  @Override
  public boolean stopParkSession() {
    this.stopDateTime = LocalDateTime.now();
    return true;
  }

  @Override
  public Vehicle getVehicle() {
    return this.vehicle;
  }

  @Override
  public DriverType getDriverType() {
    return driverType;
  }

  @Override
  public long getTotalTime() {
    if (stopDateTime == null) {
      return 0;

    } else if (startDateTime.isEqual(stopDateTime)) {
      return 0;

    } else {
      long numberOfStartedHours = 1;
      return ChronoUnit.HOURS.between(startDateTime, stopDateTime) + numberOfStartedHours;
    }
  }

  @Override
  public String toString() {
    return "ParkSession{"
        + "startDateTime="
        + startDateTime
        + ", stopDateTime="
        + stopDateTime
        + ", vehicle="
        + vehicle
        + '}';
  }
}
