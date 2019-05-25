package oose.dea.lotusterhaar.service.rest;

import oose.dea.lotusterhaar.model.TracksOverview;
import oose.dea.lotusterhaar.dao.TokenExpiredException;

public interface TrackService {
    TracksOverview getTracks(int id, String token) throws TokenExpiredException;
}
