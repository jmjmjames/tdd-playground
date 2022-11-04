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
                LocalDate.of(2022, 11, 4),
                10_000,
                LocalDate.of(2022, 12, 4));

        assertExpiryDate(
                LocalDate.of(2022, 11, 11),
                10_000,
                LocalDate.of(2022, 12, 11));
    }

    @Test
    void 납부일과_한달_뒤_일자가_같지_않음() {
        assertExpiryDate(
                LocalDate.of(2022, 1, 31),
                10_000,
                LocalDate.of(2022, 2, 28));

        assertExpiryDate(
                LocalDate.of(2022, 5, 31),
                10_000,
                LocalDate.of(2022, 6, 30));

        assertExpiryDate(
                LocalDate.of(2020, 1, 31),
                10_000,
                LocalDate.of(2020, 2, 29));
    }


    private void assertExpiryDate(LocalDate billingDate, int payAmount, LocalDate expectedExpiryDate) {
        ExpiryDateCalculator calculator = new ExpiryDateCalculator();
        LocalDate realExpiryDate = calculator.calculateExpiryDate(billingDate, payAmount);
        assertThat(realExpiryDate).isEqualTo(expectedExpiryDate);
    }

}
