package tdd.chap09.user.service;

import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tdd.chap09.user.entity.User;
import tdd.chap09.user.exception.DupIdException;
import tdd.chap09.user.exception.WeakPasswordException;
import tdd.chap09.user.repository.UserRepository;
import tdd.chap09.user.service.emailnotifier.EmailNotifier;
import tdd.chap09.user.service.passwordchecker.WeakPasswordChecker;

@Service
@RequiredArgsConstructor
public class UserService {
    private final WeakPasswordChecker passwordChecker;
    private final UserRepository userRepository;
    private final EmailNotifier emailNotifier;

    @Transactional
    public void register(String id, String pwd, String email) {
        if (passwordChecker.checkPasswordWeak(pwd)) {
            throw new WeakPasswordException();
        }
        Optional<User> findUser = userRepository.findById(id);
        if (findUser.isPresent()) {
            throw new DupIdException();
        }
        userRepository.save(User.of(id, pwd, email));

        emailNotifier.sendRegisterEmail(email);
    }
}
