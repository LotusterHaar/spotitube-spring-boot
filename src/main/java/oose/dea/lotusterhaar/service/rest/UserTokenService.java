package oose.dea.lotusterhaar.service.rest;

import oose.dea.lotusterhaar.model.UserToken;

public interface UserTokenService {

    UserToken createNewUserToken(String user);

    boolean tokenExpired(UserToken userToken);
}
