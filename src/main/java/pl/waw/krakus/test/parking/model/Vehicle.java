package pl.waw.krakus.test.parking.model;

import java.util.Objects;

public class Vehicle {
  private String registrationNumber;

    public Vehicle(String registrationNumber) {
        Objects.requireNonNull(registrationNumber, "registrationNumber cannot be null value");
        this.registrationNumber = registrationNumber;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    @Override
    public String toString() {
        return "Vehicle{" + "registrationNumber='" + registrationNumber + '\'' + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
          return true;
        }
        if (o == null || getClass() != o.getClass()) {
          return false;
        }
        Vehicle vehicle = (Vehicle) o;
        return Objects.equals(registrationNumber, vehicle.registrationNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(registrationNumber);
    }
}
