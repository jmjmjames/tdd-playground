package tdd.chap07.autodebit.dto;

import java.time.LocalDateTime;

public class AutoDebitInfo {

    private String userId;
    private String cardNumber;
    private LocalDateTime registerTime;
    private LocalDateTime updateTime;

    public AutoDebitInfo(String userId, String cardNumber, LocalDateTime registerTime) {
        this.userId = userId;
        this.cardNumber = cardNumber;
        this.registerTime = registerTime;
        this.updateTime = registerTime;
    }

    public String getUserId() {
        return userId;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public LocalDateTime getRegisterTime() {
        return registerTime;
    }

    public LocalDateTime getUpdateTime() {
        return updateTime;
    }

    public void changeCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
        this.updateTime = LocalDateTime.now();
    }
}
