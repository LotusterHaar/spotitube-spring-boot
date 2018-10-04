package oose.dea.lotusterhaar;

import javax.security.auth.login.LoginException;

public class LoginService {

    private LoginService loginService = new LoginService();

    public UserToken login(Account user) throws LoginException {
        if (user.getUser().equals("meron") && user.getPassword().equals("meronpass")) {
            UserToken token = new UserToken("1234-1234-1234-1234", "Meron Brouwer");
            return token;
        } else {
            throw new LoginException("Invalid login credentials");
        }
    }
}
