package pl.waw.krakus.test.parking.model

import org.javamoney.moneta.Money
import spock.lang.Specification
import spock.lang.Unroll

import javax.money.CurrencyUnit
import javax.money.Monetary

class FeeCalculatorTest extends Specification {

    @Unroll
    def "should Calculate Fee for #driverType driver for #minutes minutes"() {
        given:
        ParkSessionInterface parkSession = new ParkSession(new Vehicle("ABC123"), driverType)
        CurrencyUnit currencyUnit = Monetary.getCurrency("PLN")
        FeeCalulator feeCalulator = new FeeCalulator(currencyUnit)

        when: "session is stopped immediately and it's time == 0"
        parkSession.stopParkSession()
        parkSession.stopDateTime = parkSession.startDateTime

        then:
        assert parkSession.getTotalTime() == 0
        feeCalulator.calculateFee(parkSession) == Money.of(0, currencyUnit)

        when:
        parkSession.stopDateTime = parkSession.stopDateTime.plusMinutes(minutes)
        assert parkSession.getTotalTime() == Math.floor(minutes / 60 + 1)

        then:
        feeCalulator.calculateFee(parkSession) == Money.of(fee, currencyUnit)

        where:
        driverType         | minutes || fee
        DriverType.REGULAR | 1       || 1
        DriverType.REGULAR | 30      || 1
        DriverType.REGULAR | 60      || 1 + 2
        DriverType.REGULAR | 61      || 1 + 2
        DriverType.REGULAR | 120     || 1 + 2 + (1.5 * 2)
        DriverType.REGULAR | 121     || 1 + 2 + (1.5 * 2)
        DriverType.REGULAR | 180     || 1 + 2 + (1.5 * 2) + (1.5 * 3)
        DriverType.REGULAR | 181     || 1 + 2 + (1.5 * 2) + (1.5 * 3)
        DriverType.VIP     | 1       || 0
        DriverType.VIP     | 30      || 0
        DriverType.VIP     | 60      || 0 + 2
        DriverType.VIP     | 61      || 0 + 2
        DriverType.VIP     | 120     || 0 + 2 + (1.5 * 2)
        DriverType.VIP     | 121     || 0 + 2 + (1.5 * 2)
        DriverType.VIP     | 180     || 0 + 2 + (1.5 * 2) + (1.5 * 3)
        DriverType.VIP     | 181     || 0 + 2 + (1.5 * 2) + (1.5 * 3)

    }
}
