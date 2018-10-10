package oose.dea.lotusterhaar;

import javax.security.auth.login.LoginException;

public class LoginService {

    private LocalStorage localStorage;

    public UserToken login(Account user) throws LoginException {
        if (user.getUser().equals("Lotus") && user.getPassword().equals("pass")) {
            UserToken token = new UserToken("1234-1234-1234-1234", "Lotus ter Haar");
            localStorage = new LocalStorage(token.getToken());
            return token;
        } else {
            throw new LoginException("Invalid login credentials");
        }
    }
}
