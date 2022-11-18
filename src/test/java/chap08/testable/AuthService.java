package chap08.testable;

import chap08.auth.AuthUtil;

public class AuthService {
    private String authKey = "someKey";

    public int authenticate(String id, String pwd) {
        boolean authorized = AuthUtil.authorize(authKey);
        if (authorized) {
            return AuthUtil.authenticate(id, pwd);
        } else {
            return -1;
        }
    }
}
