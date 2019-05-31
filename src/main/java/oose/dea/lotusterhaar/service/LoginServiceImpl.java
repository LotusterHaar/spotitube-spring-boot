package oose.dea.lotusterhaar.service;

import oose.dea.lotusterhaar.model.Account;
import oose.dea.lotusterhaar.model.UserToken;
import oose.dea.lotusterhaar.dao.AccountRepository;
import org.springframework.stereotype.Service;

import javax.security.auth.login.LoginException;

@Service
public class LoginServiceImpl implements LoginService {


    private AccountRepository accountRepository;


    private UserTokenService userTokenService;

    public LoginServiceImpl(AccountRepository accountRepository, UserTokenService userTokenService){
        this.accountRepository = accountRepository;
        this.userTokenService = userTokenService;
    }

    @Override
    public UserToken login(Account user) throws LoginException {
        Account account = accountRepository.findByUser(user.getUser());

        if (account != null && user.getPassword().equals(account.getPassword())) {
            return userTokenService.createNewUserToken(user.getUser());
        } else {
            throw new LoginException("Invalid login credentials");
        }
    }
}
