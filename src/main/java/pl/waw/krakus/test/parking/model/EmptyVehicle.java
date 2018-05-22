package pl.waw.krakus.test.parking.model;

public class EmptyVehicle extends Vehicle {

  public EmptyVehicle() {
    super("");
  }

  @Override
  public String toString() {
    return "EmptyVehicle{}";
  }
}
