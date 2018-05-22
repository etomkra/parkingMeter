package pl.waw.krakus.test.parking.model;

public interface ParkSessionInterface {

  boolean isNotFinished();

  boolean stopParkSession();

  Vehicle getVehicle();

  DriverType getDriverType();

  long getTotalTime();
}
