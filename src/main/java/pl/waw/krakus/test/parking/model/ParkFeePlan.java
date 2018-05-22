package pl.waw.krakus.test.parking.model;

import java.util.Objects;
import javax.money.CurrencyUnit;

public class ParkFeePlan {
  private CurrencyUnit currencyUnit;
  private double firstHourPrice;
  private double secondHourPrice;
  private double otherHoursMultiplier;

  public ParkFeePlan(
      CurrencyUnit currencyUnit, double firstHour, double secondHour, double otherHoursMultiplier) {
    Objects.requireNonNull(currencyUnit, "currencyUnit cannot be null");
    this.currencyUnit = currencyUnit;
    this.firstHourPrice = firstHour;
    this.secondHourPrice = secondHour;
    this.otherHoursMultiplier = otherHoursMultiplier;
  }

  public CurrencyUnit getCurrencyUnit() {
    return currencyUnit;
  }

  public double getFirstHourPrice() {
    return firstHourPrice;
  }

  public double getSecondHourPrice() {
    return secondHourPrice;
  }

  public double getOtherHoursMultiplier() {
    return otherHoursMultiplier;
  }

  @Override
  public String toString() {
    return "ParkFeePlan{"
        + "currencyUnit="
        + currencyUnit
        + ", firstHourPrice="
        + firstHourPrice
        + ", secondHourPrice="
        + secondHourPrice
        + ", otherHoursMultiplier="
        + otherHoursMultiplier
        + '}';
  }
}
