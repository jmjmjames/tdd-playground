package chap07.user.domain.registration;

import chap07.user.domain.validator.WeakPasswordChecker;
import chap07.user.exception.DupIdException;
import chap07.user.exception.WeakPasswordException;

public class UserRegister {
    private final WeakPasswordChecker passwordChecker;
    private final UserRepository userRepository;

    public UserRegister(WeakPasswordChecker passwordChecker, UserRepository userRepository) {
        this.passwordChecker = passwordChecker;
        this.userRepository = userRepository;
    }

    public void register(String id, String pwd, String email) {
        if (passwordChecker.checkPasswordWeak(pwd)) {
            throw new WeakPasswordException();
        }
        User user = userRepository.findById(id);
        if (user != null) {
            throw new DupIdException();
        }
        userRepository.save(new User(id, pwd, email));
    }
}