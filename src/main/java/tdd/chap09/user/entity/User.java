package tdd.chap09.user.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class User {
    @Id
    private String id;
    private String password;
    private String email;

    public static User of(String id, String password, String email) {
        User user = new User();
        user.id = id;
        user.password = password;
        user.email = email;
        return user;
    }
}
