package oose.dea.lotusterhaar.services.rest;

import oose.dea.lotusterhaar.domain.Account;
import oose.dea.lotusterhaar.domain.UserToken;

import javax.security.auth.login.LoginException;

public interface LoginService {
    UserToken login(Account user) throws LoginException;
}
