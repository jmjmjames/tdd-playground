package tdd.chap03;

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
                        .billingDate(LocalDate.of(2022, 1, 31))
                        .payAmount(10_000)
                        .build(),
                LocalDate.of(2022, 2, 28));

        assertExpiryDate(
                PayData.builder()
                        .billingDate(LocalDate.of(2022, 5, 31))
                        .payAmount(10_000)
                        .build(),
                LocalDate.of(2022, 6, 30));

        assertExpiryDate(
                PayData.builder()
                        .billingDate(LocalDate.of(2020, 1, 31))
                        .payAmount(10_000)
                        .build(),
                LocalDate.of(2020, 2, 29));
    }

    @Test
    void 첫_납부일과_만료일의_일자가_같지_않을때_1만원_납부하면_첫_납부일_기준으로_다음_만료일_정함() {
        PayData payData = PayData.builder()
                .firstBillingDate(LocalDate.of(2022, 1, 31))
                .billingDate(LocalDate.of(2022, 2, 28))
                .payAmount(10_000)
                .build();

        assertExpiryDate(payData, LocalDate.of(2022, 3, 31));

        PayData payData2 = PayData.builder()
                .firstBillingDate(LocalDate.of(2022, 1, 30))
                .billingDate(LocalDate.of(2022, 2, 28))
                .payAmount(10_000)
                .build();

        assertExpiryDate(payData2, LocalDate.of(2022, 3, 30));

        PayData payData3 = PayData.builder()
                .firstBillingDate(LocalDate.of(2022, 5, 31))
                .billingDate(LocalDate.of(2022, 6, 30))
                .payAmount(10_000)
                .build();

        assertExpiryDate(payData3, LocalDate.of(2022, 7, 31));
    }

    @Test
    void 이만원_이상_납부하면_비례해서_만료일_계산() {
        assertExpiryDate(
                PayData.builder()
                        .billingDate(LocalDate.of(2022, 11, 5))
                        .payAmount(20_000)
                        .build(),
                LocalDate.of(2023, 1, 5));
    }

    @Test
    void 첫_납부일과_만료일의_일자가_다를때_2만원_이상_납부하면_첫_납부일_기준으로_다음_만료일_정함() {
        assertExpiryDate(
                PayData.builder()
                        .firstBillingDate(LocalDate.of(2022, 1, 31))
                        .billingDate(LocalDate.of(2022, 2, 28))
                        .payAmount(20_000)
                        .build(),
                LocalDate.of(2022, 4, 30));

        assertExpiryDate(
                PayData.builder()
                        .firstBillingDate(LocalDate.of(2022, 1, 31))
                        .billingDate(LocalDate.of(2022, 2, 28))
                        .payAmount(40_000)
                        .build(),
                LocalDate.of(2022, 6, 30));

        assertExpiryDate(
                PayData.builder()
                        .firstBillingDate(LocalDate.of(2022, 3, 31))
                        .billingDate(LocalDate.of(2022, 4, 30))
                        .payAmount(30_000)
                        .build(),
                LocalDate.of(2022, 7, 31));
    }

    @Test
    void 십만원을_납부하면_1년_제공() {
        assertExpiryDate(
                PayData.builder()
                        .billingDate(LocalDate.of(2022, 11, 6))
                        .payAmount(100_000)
                        .build(),
                LocalDate.of(2023, 11, 6));
    }

    private void assertExpiryDate(PayData payData, LocalDate expectedExpiryDate) {
        ExpiryDateCalculator calculator = new ExpiryDateCalculator();
        LocalDate realExpiryDate = calculator.calculateExpiryDate(payData);
        assertThat(realExpiryDate).isEqualTo(expectedExpiryDate);
    }

}
