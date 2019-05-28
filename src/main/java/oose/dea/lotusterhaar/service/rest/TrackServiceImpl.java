package oose.dea.lotusterhaar.service.rest;

import oose.dea.lotusterhaar.model.TracksOverview;
import oose.dea.lotusterhaar.model.UserToken;
import oose.dea.lotusterhaar.dao.UserTokenRepository;
import oose.dea.lotusterhaar.dao.TokenExpiredException;
import oose.dea.lotusterhaar.dao.TrackDAO;
import org.springframework.stereotype.Service;

@Service
public class TrackServiceImpl implements TrackService {
    private static final String tokenExpired = "Token has expired!";

    private TrackDAO trackDAO;

    private UserTokenRepository userTokenRepository;

    private UserTokenService userTokenService;

    public TrackServiceImpl(TrackDAO trackDAO, UserTokenRepository userTokenRepository, UserTokenService userTokenService) {
        this.trackDAO = trackDAO;
        this.userTokenRepository = userTokenRepository;
        this.userTokenService = userTokenService;
    }

    @Override
    public TracksOverview getTracks(int id, String token) throws TokenExpiredException {
        UserToken userToken = userTokenRepository.findByToken(token);
        if (!userTokenService.tokenExpired(userToken)) {
            return trackDAO.getTracks(id);
        } else {
            throw new TokenExpiredException(tokenExpired);
        }
    }
}
