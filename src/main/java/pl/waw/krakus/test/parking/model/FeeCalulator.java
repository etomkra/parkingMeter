package pl.waw.krakus.test.parking.model;

import javax.money.CurrencyUnit;
import javax.money.MonetaryAmount;

public class FeeCalulator {
  private CurrencyUnit currencyUnit;
  private FeeStrategy feeStrategy;

  public FeeCalulator(CurrencyUnit currencyUnit) {
    this.currencyUnit = currencyUnit;
  }

  MonetaryAmount calculateFee(ParkSession parkSession) {

    if (parkSession.getDriverType().equals(DriverType.REGULAR)) {
      feeStrategy = new FeeStrategyThreeRates(new ParkFeePlan(currencyUnit, 1, 2, 1.5));

    } else if (parkSession.getDriverType().equals(DriverType.VIP)) {
      feeStrategy = new FeeStrategyThreeRates(new ParkFeePlan(currencyUnit, 0, 2, 1.5));

    } else {
      throw new RuntimeException("Unknown DriverType");
    }
    return feeStrategy.calculateFee(parkSession);
  }
}
