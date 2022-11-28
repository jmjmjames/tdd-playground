package tdd.chap09.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tdd.chap09.user.entity.User;

public interface UserRepository extends JpaRepository<User, String> {
}
