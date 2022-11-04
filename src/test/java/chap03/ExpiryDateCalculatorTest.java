package chap03;

import java.time.LocalDate;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ExpiryDateCalculatorTest {

    @Test
    @DisplayName("만원 납부하면 한달 뒤가 만료일이 됨")
    void pay_10000_won_Then_expiry_date_should_be_one_month_later() {
        LocalDate billingDate = LocalDate.of(2022, 11, 4);
        int payAmount = 10_000;

        ExpiryDateCalculator calculator = new ExpiryDateCalculator();
        LocalDate expiryDate = calculator.calculateExpiryDate(billingDate, payAmount);

        Assertions.assertThat(expiryDate).isEqualTo(LocalDate.of(2022, 12, 4));
    }


}
