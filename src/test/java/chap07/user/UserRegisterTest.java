package chap07.user;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import chap07.user.domain.registration.MemoryUserRepository;
import chap07.user.domain.registration.User;
import chap07.user.domain.registration.UserRegister;
import chap07.user.domain.validator.StubWeakPasswordChecker;
import chap07.user.exception.DupIdException;
import chap07.user.exception.WeakPasswordException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class UserRegisterTest {
    private UserRegister userRegister;
    private final StubWeakPasswordChecker stubWeakPasswordChecker = new StubWeakPasswordChecker();
    private final MemoryUserRepository fakeRepository = new MemoryUserRepository();

    @BeforeEach
    void setUp() {
        userRegister = new UserRegister(stubWeakPasswordChecker, fakeRepository);
    }

    @DisplayName("[예외] 약한 암호면 가입이 실패")
    @Test
    void weakPassword() {
        stubWeakPasswordChecker.setWeak(true);  // 암호가 약하다고 응답하도록 설정

        assertThatThrownBy(() -> userRegister.register("id", "pwd", "email"))
                .isInstanceOf(WeakPasswordException.class);
    }

    @DisplayName("[예외] 이미 같은 ID가 존재하면 가입 실패")
    @Test
    void dupIdExists() {
        fakeRepository.save(new User("id", "pwd1", "eamil@eamil.com"));
        assertThatThrownBy(() -> userRegister.register("id", "pwd2", "email"))
                .isInstanceOf(DupIdException.class);
    }

    @DisplayName("[정상] 같은 ID가 없으면 가입 성공")
    @Test
    void noDupId_RegisterSuccess() {
        userRegister.register("id", "pwd", "email");

        User findUser = fakeRepository.findById("id");
        assertThat(findUser.getId()).isEqualTo("id");
        assertThat(findUser.getEmail()).isEqualTo("email");
    }
}
