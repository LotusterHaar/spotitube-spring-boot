package oose.dea.lotusterhaar.service.local;

import oose.dea.lotusterhaar.model.Account;
import oose.dea.lotusterhaar.model.LocalStorage;
import oose.dea.lotusterhaar.model.UserToken;
import oose.dea.lotusterhaar.service.rest.LoginService;

import javax.security.auth.login.LoginException;


public class LoginLocalServiceImpl implements LoginService {

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
