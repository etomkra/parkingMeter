package pl.waw.krakus.test.parking.model

import spock.lang.Specification
import spock.lang.Unroll

import javax.money.Monetary

class ParkFeePlanTest extends Specification {
    def "should instantiate ParkFeePlan"() {
        given:
        def currencyUnit = Monetary.getCurrency("USD")

        when:
        def feePlan = new ParkFeePlan(currencyUnit, 0, 1, 2)

        then:
        noExceptionThrown()

        and:
        feePlan.getFirstHourPrice() == 0
        feePlan.getSecondHourPrice() == 1
        feePlan.getOtherHoursMultiplier() == 2
    }

    @Unroll
    def "should thrown an Exception due to null parameter #parameter"() {
        when:
        new ParkFeePlan(currencyUnit, firstHour, secondHour, otherMultiplier)

        then:
        thrown(Exception)

        where:
        parameter         || currencyUnit                | firstHour | secondHour | otherMultiplier
        "currencyUnit"    || null                        | 0         | 1          | 2
        "firstHour"       || Monetary.getCurrency("USD") | null      | 1          | 2
        "secondHour"      || Monetary.getCurrency("USD") | 0         | null       | 2
        "otherMultiplier" || Monetary.getCurrency("USD") | 0         | 1          | null
    }
}
