package oose.dea.lotusterhaar.service.rest;

import oose.dea.lotusterhaar.model.Account;
import oose.dea.lotusterhaar.model.UserToken;
import oose.dea.lotusterhaar.dao.AccountRepository;
import oose.dea.lotusterhaar.dao.UserTokenRepository;
import org.springframework.stereotype.Service;

import javax.security.auth.login.LoginException;

@Service
public class LoginServiceImpl implements LoginService {


    private AccountRepository accountRepository;

    private UserTokenRepository userTokenRepository;

    private UserTokenService userTokenService;

    public LoginServiceImpl(AccountRepository accountRepository, UserTokenRepository userTokenRepository, UserTokenService userTokenService){
        this.accountRepository = accountRepository;
        this.userTokenRepository = userTokenRepository;
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
