package oose.dea.lotusterhaar.services;

import oose.dea.lotusterhaar.domain.Account;
import oose.dea.lotusterhaar.domain.LocalStorage;
import oose.dea.lotusterhaar.domain.UserToken;

import javax.inject.Named;
import javax.security.auth.login.LoginException;

@Named("loginRestService")
public class LoginRestService {

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
