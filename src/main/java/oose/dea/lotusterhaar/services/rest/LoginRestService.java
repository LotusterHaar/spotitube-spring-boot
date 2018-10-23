package oose.dea.lotusterhaar.services.rest;

import oose.dea.lotusterhaar.domain.Account;
import oose.dea.lotusterhaar.domain.LocalStorage;
import oose.dea.lotusterhaar.domain.UserToken;
import oose.dea.lotusterhaar.persistence.AccountDAO;

import javax.inject.Inject;
import javax.inject.Named;
import javax.security.auth.login.LoginException;

@Named("loginRestService")
public class LoginRestService {

    @Inject
    private AccountDAO accountDAO;

    private LocalStorage localStorage;

    public UserToken login(Account user) throws LoginException {
        Account account = accountDAO.findAccountByUsername(user.getUser());
        System.out.println("user: ");
        System.out.println(account.getUser());
        System.out.println("pass: ");
        System.out.println(account.getPassword());
        if (account != null && user.getPassword().equals(account.getPassword())) {
            UserToken token = new UserToken("1234-1234-1234-1234", "Lotus ter Haar");
            localStorage = new LocalStorage(token.getToken());
            return token;
        } else {
            throw new LoginException("Invalid login credentials");
        }
    }
}
