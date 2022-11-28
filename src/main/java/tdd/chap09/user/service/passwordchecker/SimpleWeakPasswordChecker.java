package tdd.chap09.user.service.passwordchecker;

import java.util.Objects;
import org.springframework.stereotype.Component;

@Component
public class SimpleWeakPasswordChecker implements WeakPasswordChecker {
    @Override
    public boolean checkPasswordWeak(String pwd) {
        return Objects.isNull(pwd) || pwd.isBlank() || pwd.length() < 5;
    }
}
