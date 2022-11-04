package chap03;

import static org.assertj.core.api.Assertions.*;

import java.time.LocalDate;
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

        assertThat(expiryDate).isEqualTo(LocalDate.of(2022, 12, 4));

        LocalDate billingDate2 = LocalDate.of(2022, 11, 11);
        int payAmount2 = 10_000;

        LocalDate expiryDate2 = calculator.calculateExpiryDate(billingDate2, payAmount2);
        assertThat(expiryDate2).isEqualTo(LocalDate.of(2022, 12, 11));
    }


}
