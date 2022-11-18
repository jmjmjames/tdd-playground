package chap08.nontestable;

import chap08.auth.AuthUtil;
import chap08.auth.Customer;
import chap08.auth.CustomerRepository;
import chap08.auth.LoginResult;

public class LoginService {
    private final String authKey = "someKey";
    private final CustomerRepository customerRepository;

    public LoginService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public LoginResult login(String id, String pwd) {
        int resp = 0;
        boolean authorized = AuthUtil.authorize(authKey);
        if (authorized) {
            resp = AuthUtil.authenticate(id, pwd);
        } else {
            resp = -1;
        }

        if (resp == -1) {
            return LoginResult.badAuthKey();
        }
        if (resp == 1) {
            Customer customer = customerRepository.findById(id);
            return LoginResult.authenticated(customer);
        }
        return LoginResult.fail(resp);
    }
}
