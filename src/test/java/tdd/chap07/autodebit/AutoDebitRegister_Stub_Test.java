package tdd.chap07.autodebit;


import tdd.chap07.autodebit.domain.AutoDebitRegister;
import tdd.chap07.autodebit.dto.AutoDebitReq;
import tdd.chap07.autodebit.dto.RegisterResult;
import tdd.chap07.autodebit.repository.StubAutoDebitInfoRepository;
import tdd.chap07.autodebit.validator.StubCardNumberValidator;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class AutoDebitRegister_Stub_Test {

    private AutoDebitRegister register;
    private StubCardNumberValidator stubValidator;
    private StubAutoDebitInfoRepository stubRepository;

    @BeforeEach
    void setUp() {
        stubValidator = new StubCardNumberValidator();
        stubRepository = new StubAutoDebitInfoRepository();
        register = new AutoDebitRegister(stubValidator, stubRepository);
    }

    @Test
    void invalidCard() {
        stubValidator.setInvalidNo("1234567890123");

        AutoDebitReq req = new AutoDebitReq("user1", "1234567890123");
        RegisterResult result = register.register(req);

        Assertions.assertThat(result.getValidity()).isEqualTo(CardValidity.INVALID);
    }

    @Test
    void theftCard() {
        stubValidator.setTheftNo("1234567890123456");

        AutoDebitReq req = new AutoDebitReq("user1", "1234567890123456");
        RegisterResult result = register.register(req);

        Assertions.assertThat(result.getValidity()).isEqualTo(CardValidity.THEFT);
    }
}
