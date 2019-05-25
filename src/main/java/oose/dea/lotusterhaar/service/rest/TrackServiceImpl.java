package oose.dea.lotusterhaar.service.rest;

import oose.dea.lotusterhaar.model.TracksOverview;
import oose.dea.lotusterhaar.model.UserToken;
import oose.dea.lotusterhaar.dao.TokenDAO;
import oose.dea.lotusterhaar.dao.TokenExpiredException;
import oose.dea.lotusterhaar.dao.TrackDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("trackService")
public class TrackServiceImpl implements TrackService {
    private static final String tokenExpired = "Token has expired!";

    @Autowired
    private TrackDAO trackDAO;

    @Autowired
    private TokenDAO tokenDAO;

    @Override
    public TracksOverview getTracks(int id, String token) throws TokenExpiredException {
        UserToken userToken = tokenDAO.getUserToken(token);
        if (!tokenDAO.tokenExpired(userToken)) {
            return trackDAO.getTracks(id);
        } else {
            throw new TokenExpiredException(tokenExpired);
        }
    }
}
