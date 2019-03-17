package oose.dea.lotusterhaar.services.rest;

import oose.dea.lotusterhaar.domain.TrackOverview;
import oose.dea.lotusterhaar.persistence.TokenExpiredException;
import oose.dea.lotusterhaar.persistence.TrackDAO;

import javax.inject.Inject;
import javax.inject.Named;

@Named("trackRestService")
public class TrackRestService {
    private static final String tokenExpired = "Token has expired!";

    @Inject
    private TrackDAO trackDAO;

    public TrackOverview getTracks(int id, String token) throws Exception {
        boolean validToken = true;
        if (validToken) {
            return trackDAO.getTracks(id);
        } else {
            throw new TokenExpiredException(tokenExpired);
        }
    }
}
