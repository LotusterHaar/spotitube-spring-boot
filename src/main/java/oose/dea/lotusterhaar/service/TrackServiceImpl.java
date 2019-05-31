package oose.dea.lotusterhaar.service;

import oose.dea.lotusterhaar.model.TracksOverview;
import oose.dea.lotusterhaar.model.UserToken;
import oose.dea.lotusterhaar.dao.UserTokenRepository;
import oose.dea.lotusterhaar.dao.TokenExpiredException;
import oose.dea.lotusterhaar.dao.TracksViewRepository;
import org.springframework.stereotype.Service;

@Service
public class TrackServiceImpl implements TrackService {
    private static final String tokenExpired = "Token has expired!";

    private TracksViewRepository tracksViewRepository;

    private UserTokenRepository userTokenRepository;

    private UserTokenService userTokenService;

    public TrackServiceImpl(TracksViewRepository tracksViewRepository, UserTokenRepository userTokenRepository, UserTokenService userTokenService) {
        this.tracksViewRepository = tracksViewRepository;
        this.userTokenRepository = userTokenRepository;
        this.userTokenService = userTokenService;
    }

    @Override
    public TracksOverview getTracks(int id, String token) throws TokenExpiredException {
        UserToken userToken = userTokenRepository.findByToken(token);
        if (!userTokenService.tokenExpired(userToken)) {
            return tracksViewRepository.getTracks(id);
        } else {
            throw new TokenExpiredException(tokenExpired);
        }
    }
}
