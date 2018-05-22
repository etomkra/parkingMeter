package pl.waw.krakus.test.parking.model;

import javax.money.MonetaryAmount;

public interface FeeStrategy {
  MonetaryAmount calculateFee(ParkSessionInterface parkSession);
}
