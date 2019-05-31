package oose.dea.lotusterhaar.dao;

import oose.dea.lotusterhaar.model.PlaylistsView;
import org.springframework.data.repository.CrudRepository;

public interface PlaylistsViewRepository extends CrudRepository<PlaylistsView, Integer> {

    int findByTrackId(int trackId);

    int findByPlaylistId(int playlistId);

}
