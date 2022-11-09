package chap07.autodebit.validator;

import chap07.autodebit.CardValidity;

/**
 * StubCardNumberValidator는 실제 카드번호 검증 기능을 구현하지 않는다.
 * 대신 단순한 구현으로 실제 구현을 대체한다.
 * validate() 메서드는 invalidNo 필드와 동일한 카드번호면 결과로 INVALID return
 * 그렇지않으면 VALID를 리턴한다.
 */
public class StubCardNumberValidator extends CardNumberValidator {

    private String invalidNo;
    private String theftNo;

    public void setInvalidNo(String invalidNo) {
        this.invalidNo = invalidNo;
    }

    public void setTheftNo(String theftNo) {
        this.theftNo = theftNo;
    }

    @Override
    public CardValidity validate(String cardNumber) {
        if (invalidNo != null && invalidNo.equals(cardNumber)) {
            return CardValidity.INVALID;
        }
        if (theftNo != null && theftNo.equals(cardNumber)) {
            return CardValidity.THEFT;
        }

        return CardValidity.VALID;
    }
}
