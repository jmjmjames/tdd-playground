package tdd.chap09.user.web.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserRegisterRequest {
    private String id;
    private String pwd;
    private String email;
}
