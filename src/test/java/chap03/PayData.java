package chap03;

import java.time.LocalDate;

public class PayData {

    private LocalDate firstBillingDate;
    private LocalDate billingDate;
    private int amount;

    private PayData() {
    }

    public LocalDate getFirstBillingDate() {
        return firstBillingDate;
    }

    public LocalDate getBillingDate() {
        return billingDate;
    }

    public int getAmount() {
        return amount;
    }


    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private PayData payData = new PayData();

        public Builder firstBillingDate(LocalDate firstBillingDate) {
            payData.firstBillingDate = firstBillingDate;
            return this;
        }

        public Builder billingDate(LocalDate billingDate) {
            payData.billingDate = billingDate;
            return this;
        }

        public Builder payAmount(int payAmount) {
            payData.amount = payAmount;
            return this;
        }

        public PayData build() {
            return payData;
        }
    }
}
