package oose.dea.lotusterhaar.service.rest;

import oose.dea.lotusterhaar.model.Account;
import oose.dea.lotusterhaar.model.UserToken;
import oose.dea.lotusterhaar.dao.AccountDAO;
import oose.dea.lotusterhaar.dao.TokenDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.security.auth.login.LoginException;

@Service
public class LoginServiceImpl implements LoginService {


    private AccountDAO accountDAO;

    private TokenDAO tokenDAO;

    public LoginServiceImpl(AccountDAO accountDAO, TokenDAO tokenDAO){
        this.accountDAO = accountDAO;
        this.tokenDAO = tokenDAO;
    }

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
