package chap07.autodebit;

import static chap07.autodebit.CardValidity.*;
import static org.junit.jupiter.api.Assertions.*;

import chap07.autodebit.repository.AutoDebitInfoRepository;
import chap07.autodebit.repository.JpaAutoDebitInfoRepository;
import chap07.autodebit.validator.CardNumberValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class AutoDebitRegisterTest {

    private AutoDebitRegister register;

    @BeforeEach
    void setUp() {
        CardNumberValidator validator = new CardNumberValidator();
        AutoDebitInfoRepository repository = new JpaAutoDebitInfoRepository();
        register = new AutoDebitRegister(validator, repository);
    }

    /**
     * 외부 업체에서 테스트 목적의 유효한 카트번호를 받아야 한다. 만약 카드번호가 한 달 뒤에 만료되면 테스트는 한 달 뒤부터 실패한다.
     * 테스드 대상에서 의존하는 요인 때문에 테스트가 어려울 때는 대역을 써서 테스트를 진행할 수 있다.
     */
    @Test
    void validCard() {
        // 업체에서 받은 테스트용 유효한 카드 번호 사용
        AutoDebitReq req = new AutoDebitReq("user1", "1234123412341234");
        RegisterResult result = this.register.register(req);
        assertEquals(VALID, result.getValidity());
    }

    @Test
    void theftCard() {
        // 업체에서 받은 도난 테스트용 카드 번호 사용
        AutoDebitReq req = new AutoDebitReq("user1", "1234567894321987");
        RegisterResult result = this.register.register(req);
        assertEquals(THEFT, result.getValidity());
    }
}
