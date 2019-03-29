package oose.dea.lotusterhaar.services.rest;

import oose.dea.lotusterhaar.domain.TracksOverview;

public interface TrackService {
    TracksOverview getTracks(int id, String token) throws Exception;
}
