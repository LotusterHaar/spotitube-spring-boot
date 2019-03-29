package oose.dea.lotusterhaar.services.rest;

import oose.dea.lotusterhaar.domain.TracksOverview;
import oose.dea.lotusterhaar.domain.UserToken;
import oose.dea.lotusterhaar.persistence.TokenDAO;
import oose.dea.lotusterhaar.persistence.TokenExpiredException;
import oose.dea.lotusterhaar.persistence.TrackDAO;

import javax.inject.Inject;
import javax.inject.Named;

@Named("trackService")
public class TrackServiceImpl implements TrackService {
    private static final String tokenExpired = "Token has expired!";

    @Inject
    private TrackDAO trackDAO;

    @Inject
    private TokenDAO tokenDAO;

    @Override
    public TracksOverview getTracks(int id, String token) throws Exception {
        UserToken userToken = tokenDAO.getUserToken(token);
        if (!tokenDAO.tokenExpired(userToken)) {
            return trackDAO.getTracks(id);
        } else {
            throw new TokenExpiredException(tokenExpired);
        }
    }
}
