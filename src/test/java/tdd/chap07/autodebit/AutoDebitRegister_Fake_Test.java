package tdd.chap07.autodebit;


import tdd.chap07.autodebit.domain.AutoDebitRegister;
import tdd.chap07.autodebit.dto.AutoDebitInfo;
import tdd.chap07.autodebit.dto.AutoDebitReq;
import tdd.chap07.autodebit.dto.RegisterResult;
import tdd.chap07.autodebit.repository.MemoryAutoDebitInfoRepository;
import tdd.chap07.autodebit.validator.StubCardNumberValidator;
import java.time.LocalDateTime;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class AutoDebitRegister_Fake_Test {

    private AutoDebitRegister register;
    private StubCardNumberValidator stubValidator;
    private MemoryAutoDebitInfoRepository repository;

    @BeforeEach
    void setUp() {
        stubValidator = new StubCardNumberValidator();
        repository = new MemoryAutoDebitInfoRepository();
        register = new AutoDebitRegister(stubValidator, repository);
    }

    @Test
    void alreadyRegistered_InfoUpdated() {
        repository.save(
                new AutoDebitInfo("user1", "111222333444", LocalDateTime.now()));


        AutoDebitReq req = new AutoDebitReq("user1", "123456789012");
        RegisterResult result = register.register(req);

        AutoDebitInfo saved = repository.findOne("user1");
        Assertions.assertThat(saved.getCardNumber()).isEqualTo("123456789012");
    }

    @Test
    void notYetRegistered_newInfoRegistered() {
        AutoDebitReq req = new AutoDebitReq("user1", "123456789012");
        RegisterResult result = register.register(req);

        AutoDebitInfo saved = repository.findOne("user1");
        Assertions.assertThat(saved.getCardNumber() ).isEqualTo("123456789012");
    }
}
