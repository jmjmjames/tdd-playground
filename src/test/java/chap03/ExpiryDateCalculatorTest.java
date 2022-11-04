package chap03;

import static org.assertj.core.api.Assertions.*;

import java.time.LocalDate;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ExpiryDateCalculatorTest {

    @Test
    @DisplayName("만원 납부하면 한달 뒤가 만료일이 됨")
    void pay_10000_won_Then_expiry_date_should_be_one_month_later() {

        assertExpiryDate(
                PayData.builder()
                        .billingDate(LocalDate.of(2022, 11, 4))
                        .payAmount(10_000)
                        .build(),
                LocalDate.of(2022, 12, 4));

        assertExpiryDate(
                PayData.builder()
                        .billingDate(LocalDate.of(2022, 11, 11))
                        .payAmount(10_000)
                        .build(),
                LocalDate.of(2022, 12, 11));
    }

    @Test
    void 납부일과_한달_뒤_일자가_같지_않음() {
        assertExpiryDate(
                PayData.builder()
                        .billingDate( LocalDate.of(2022, 1, 31))
                        .payAmount(10_000)
                        .build(),
                LocalDate.of(2022, 2, 28));

        assertExpiryDate(
                PayData.builder()
                        .billingDate( LocalDate.of(2022, 5, 31))
                        .payAmount(10_000)
                        .build(),
                LocalDate.of(2022, 6, 30));

        assertExpiryDate(
                PayData.builder()
                        .billingDate( LocalDate.of(2020, 1, 31))
                        .payAmount(10_000)
                        .build(),
                LocalDate.of(2020, 2, 29));
    }


    private void assertExpiryDate(PayData payData, LocalDate expectedExpiryDate) {
        ExpiryDateCalculator calculator = new ExpiryDateCalculator();
        LocalDate realExpiryDate = calculator.calculateExpiryDate(payData);
        assertThat(realExpiryDate).isEqualTo(expectedExpiryDate);
    }

}
