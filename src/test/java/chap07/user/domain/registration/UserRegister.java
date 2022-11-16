package chap07.user.domain.registration;

import chap07.user.domain.validator.WeakPasswordChecker;
import chap07.user.exception.WeakPasswordException;

public class UserRegister {
    private WeakPasswordChecker passwordChecker;

    public UserRegister(WeakPasswordChecker passwordChecker) {
        this.passwordChecker = passwordChecker;
    }

    public void register(String id, String pwd, String email) {
        if (passwordChecker.checkPasswordWeak(pwd)) {
            throw new WeakPasswordException();
        }
    }
}
