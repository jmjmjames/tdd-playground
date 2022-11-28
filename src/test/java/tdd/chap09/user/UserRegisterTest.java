package tdd.chap09.user;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import tdd.chap09.user.entity.User;
import tdd.chap09.user.exception.DupIdException;
import tdd.chap09.user.repository.UserRepository;
import tdd.chap09.user.service.UserService;

@SpringBootTest
public class UserRegisterTest {
    private final UserService userService;
    private final UserRepository userRepository;

    public UserRegisterTest(@Autowired UserService userService,
                            @Autowired UserRepository userRepository) {
        this.userService = userService;
        this.userRepository = userRepository;
    }

    @AfterEach
    public void tearDown() {
        userRepository.deleteAll();
    }

    @Test
    void 동일ID가_이미_존재하면_익셉션() {
        User user = User.of("jm", "password", "email@email.com");
        userRepository.save(user);

        Assertions.assertThatThrownBy(() -> userService.register(user.getId(), user.getPassword(), user.getEmail()))
                .isInstanceOf(DupIdException.class);
    }

    @Test
    void 동일ID가_존재하지_않으면_저장() {
        User user = User.of("jm", "password", "email@email.com");
        userService.register(user.getId(), user.getPassword(), user.getEmail());
        Optional<User> findUser = userRepository.findById(user.getId());

        assertThat(findUser).isNotEmpty();
        assertThat(findUser.get().getEmail())
                .isEqualTo(user.getEmail());
    }
}
