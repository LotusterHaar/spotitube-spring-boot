package oose.dea.lotusterhaar.services.rest;

import oose.dea.lotusterhaar.domain.TracksOverview;
import oose.dea.lotusterhaar.persistence.TokenExpiredException;

public interface TrackService {
    TracksOverview getTracks(int id, String token) throws TokenExpiredException;
}
