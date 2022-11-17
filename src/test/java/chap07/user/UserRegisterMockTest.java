package chap07.user;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.BDDMockito.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.mock;

import chap07.user.domain.notificaiton.EmailNotifier;
import chap07.user.domain.registration.User;
import chap07.user.domain.registration.UserRegister;
import chap07.user.domain.registration.UserRepository;
import chap07.user.domain.validator.WeakPasswordChecker;
import chap07.user.exception.WeakPasswordException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

public class UserRegisterMockTest {
    private UserRegister userRegister;
    private final WeakPasswordChecker mockPasswordChecker = mock(WeakPasswordChecker.class);
    private final UserRepository mockRepository = mock(UserRepository.class);
    private final EmailNotifier mockEmailNotifier = mock(EmailNotifier.class);

    @BeforeEach
    void setUp() {
        userRegister = new UserRegister(mockPasswordChecker, mockRepository, mockEmailNotifier);
    }

    @DisplayName("[예외] 약한 암호면 가입이 실패")
    @Test
    void weakPassword() {
        given(mockPasswordChecker.checkPasswordWeak("pwd")).willReturn(true);

        assertThatThrownBy(() -> userRegister.register("id", "pwd", "email"))
                .isInstanceOf(WeakPasswordException.class);
    }

    @DisplayName("[정상] 회원 가입시 암호 검사 수행함")
    @Test
    void checkPassword() {
        userRegister.register("id", "pwd", "email");

        then(mockPasswordChecker)
                .should()
                .checkPasswordWeak(anyString());
    }

    @DisplayName("[정상] 같은 ID가 없으면 가입 성공")
    @Test
    void noDupId_RegisterSuccess() {
        userRegister.register("id", "pwd", "email");

        ArgumentCaptor<User> captor = ArgumentCaptor.forClass(User.class);
        then(mockRepository).should().save(captor.capture());

        User findUser = captor.getValue();
        assertThat(findUser.getId()).isEqualTo("id");
        assertThat(findUser.getEmail()).isEqualTo("email");
    }

    @DisplayName("[정상] 가입하면 메일을 전송함")
    @Test
    void whenRegisterThenSendMail() {
        userRegister.register("id","pwd","email@email.com");
        ArgumentCaptor<String> captor = ArgumentCaptor.forClass(String.class);
        then(mockEmailNotifier)
                .should().sendRegisterEmail(captor.capture());

        String actualEmail = captor.getValue();
        assertThat(actualEmail).isEqualTo("email@email.com");
    }
}
