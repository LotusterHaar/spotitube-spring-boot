package oose.dea.lotusterhaar.service;

import oose.dea.lotusterhaar.model.UserToken;

public interface UserTokenService {

    UserToken createNewUserToken(String user);

    boolean tokenExpired(UserToken userToken);
}
