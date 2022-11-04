package chap03;

import java.time.LocalDate;

public class PayData {
    private LocalDate billingDate;
    private int amount;

    private PayData() {

    }

    public PayData(LocalDate billingDate, int amount) {
        this.billingDate = billingDate;
        this.amount = amount;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private PayData payData = new PayData();

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

    public LocalDate getBillingDate() {
        return billingDate;
    }

    public int getAmount() {
        return amount;
    }
}
