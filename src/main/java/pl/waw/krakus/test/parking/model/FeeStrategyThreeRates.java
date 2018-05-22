package pl.waw.krakus.test.parking.model;

import java.math.BigDecimal;
import javax.money.MonetaryAmount;
import org.javamoney.moneta.Money;

public class FeeStrategyThreeRates implements FeeStrategy {
  private ParkFeePlan feePlan;

  public FeeStrategyThreeRates(ParkFeePlan feePlan) {
    this.feePlan = feePlan;
  }

  @Override
  public MonetaryAmount calculateFee(ParkSessionInterface parkSession) {
    MonetaryAmount result = Money.of(0, feePlan.getCurrencyUnit());
    double sum = 0;
    long sessionTime;

    if (parkSession.isNotFinished()) {
      return result;
    }
    sessionTime = parkSession.getTotalTime();

    if (sessionTime == 0) {
      return Money.of(0, feePlan.getCurrencyUnit());
    }

    if (sessionTime <= 1) {
      sum = getFirstHourFee();
    } else if (sessionTime <= 2) {
      sum = getSecondHourFee(sum);

    } else {
      sum += getSecondHourFee(sum);
      double lastHourFee = feePlan.getSecondHourPrice();
      for (int i = 2; i < sessionTime; i++) {
        lastHourFee = lastHourFee * getOtherHoursMultiplier();
        sum += lastHourFee;
      }
    }

    return Money.of(new BigDecimal(sum), feePlan.getCurrencyUnit());
  }

  private double getOtherHoursMultiplier() {
    return feePlan.getOtherHoursMultiplier();
  }

  private double getFirstHourFee() {
    return feePlan.getFirstHourPrice();
  }

  private double getSecondHourFee(double sum) {
    sum += feePlan.getFirstHourPrice();
    sum += feePlan.getSecondHourPrice();
    return sum;
  }
}
