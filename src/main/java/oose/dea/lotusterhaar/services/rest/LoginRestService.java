package oose.dea.lotusterhaar.services.rest;

import oose.dea.lotusterhaar.domain.Account;
import oose.dea.lotusterhaar.domain.UserToken;
import oose.dea.lotusterhaar.persistence.AccountDAO;
import oose.dea.lotusterhaar.persistence.TokenDAO;

import javax.inject.Inject;
import javax.inject.Named;
import javax.security.auth.login.LoginException;

@Named("loginRestService")
public class LoginRestService {

    @Inject
    private AccountDAO accountDAO;

    @Inject
    private TokenDAO tokenDAO;

    public UserToken login(Account user) throws LoginException {
        Account account = accountDAO.findAccountByUsername(user.getUser());

        if (account != null && user.getPassword().equals(account.getPassword())) {
            UserToken token = new UserToken("1234-1234-1234-1234", "Lotus ter Haar");
            return tokenDAO.createNewUserToken(user.getUser());
        } else {
            throw new LoginException("Invalid login credentials");
        }
    }
}
