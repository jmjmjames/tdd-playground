package tdd.chap07.user.domain.registration;

public interface UserRepository {
    void save(User user);

    User findById(String id);
}
