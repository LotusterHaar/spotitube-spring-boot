package oose.dea.lotusterhaar.service.rest;

import oose.dea.lotusterhaar.model.Account;
import oose.dea.lotusterhaar.model.UserToken;
import oose.dea.lotusterhaar.dao.AccountDAO;
import oose.dea.lotusterhaar.dao.TokenDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.security.auth.login.LoginException;

@Component("loginService")
public class LoginServiceImpl implements LoginService {

    @Autowired
    private AccountDAO accountDAO;

    @Autowired
    private TokenDAO tokenDAO;

    @Override
    public UserToken login(Account user) throws LoginException {
        Account account = accountDAO.findAccountByUsername(user.getUser());

        if (account != null && user.getPassword().equals(account.getPassword())) {
            return tokenDAO.createNewUserToken(user.getUser());
        } else {
            throw new LoginException("Invalid login credentials");
        }
    }
}
