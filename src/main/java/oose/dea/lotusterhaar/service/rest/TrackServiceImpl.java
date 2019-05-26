package oose.dea.lotusterhaar.service.rest;

import oose.dea.lotusterhaar.model.TracksOverview;
import oose.dea.lotusterhaar.model.UserToken;
import oose.dea.lotusterhaar.dao.TokenDAO;
import oose.dea.lotusterhaar.dao.TokenExpiredException;
import oose.dea.lotusterhaar.dao.TrackDAO;
import org.springframework.stereotype.Service;

@Service
public class TrackServiceImpl implements TrackService {
    private static final String tokenExpired = "Token has expired!";

    private TrackDAO trackDAO;

    private TokenDAO tokenDAO;

    public TrackServiceImpl(TrackDAO trackDAO, TokenDAO tokenDAO) {
        this.trackDAO = trackDAO;
        this.tokenDAO = tokenDAO;
    }

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
