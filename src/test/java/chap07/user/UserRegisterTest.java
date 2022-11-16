package chap07.user;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import chap07.user.domain.registration.UserRegister;
import chap07.user.domain.validator.StubWeakPasswordChecker;
import chap07.user.exception.WeakPasswordException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class UserRegisterTest {
    private UserRegister userRegister;
    private final StubWeakPasswordChecker stubWeakPasswordChecker = new StubWeakPasswordChecker();

    @BeforeEach
    void setUp() {
        userRegister = new UserRegister(stubWeakPasswordChecker);
    }

    @DisplayName("약한 암호면 가입이 실패")
    @Test
    void weakPassword() {
        stubWeakPasswordChecker.setWeak(true);  // 암호가 약하다고 응답하도록 설정

        assertThatThrownBy(() -> userRegister.register("id", "pwd", "email"))
                .isInstanceOf(WeakPasswordException.class);
    }
}
