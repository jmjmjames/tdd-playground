package tdd.chap08.payinfo;

public class PayInfo {
    private final String datetime;
    private final String trNumber;
    private final int amounts;

    public PayInfo(String datetime, String trNumber, int amounts) {
        this.datetime = datetime;
        this.trNumber = trNumber;
        this.amounts = amounts;
    }

    public String getDatetime() {
        return datetime;
    }

    public String getTrNumber() {
        return trNumber;
    }

    public int getAmounts() {
        return amounts;
    }
}
