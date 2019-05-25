package oose.dea.lotusterhaar.dao;

import oose.dea.lotusterhaar.model.SongTrack;
import oose.dea.lotusterhaar.model.Track;
import oose.dea.lotusterhaar.model.TracksOverview;
import oose.dea.lotusterhaar.model.VideoTrack;
import org.springframework.beans.factory.annotation.Autowired;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class TrackDAO {

    @Autowired
    ConnectionFactory connectionFactory;

    public TracksOverview getTracks(int id) {
        TracksOverview tracks = new TracksOverview();
        ArrayList<Track> songs = getSongTracks(id);
        ArrayList<Track> videos = getVideoTracks(id);
        tracks.addTracks(songs);
        tracks.addTracks(videos);
        return tracks;
    }

    public ArrayList<Track> getSongTracks(int id) {
        ArrayList<Track> tracks = new ArrayList();
        try (
                Connection connection = connectionFactory.getConnection();
                PreparedStatement statement = connection.prepareStatement("SELECT * FROM spotitube.songtracks_view WHERE spotitube.songtracks_view.id NOT IN (SELECT playlist_has_tracks.track_id FROM spotitube.playlist_has_tracks WHERE playlist_has_tracks.playlist_id = ?)\n")
        ) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                tracks.add(
                        new SongTrack(
                                resultSet.getInt("id"),
                                resultSet.getString("title"),
                                resultSet.getString("performer"),
                                resultSet.getInt("duration"),
                                resultSet.getInt("playcount"),
                                resultSet.getBoolean("offlineAvailable"),
                                resultSet.getString("album")
                        )
                );
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return tracks;
    }

    public ArrayList<Track> getVideoTracks(int id) {
        ArrayList<Track> tracks = new ArrayList();
        try (
                Connection connection = connectionFactory.getConnection();
                PreparedStatement statement = connection.prepareStatement("SELECT * FROM spotitube.videotracks_view WHERE spotitube.videotracks_view.id NOT IN (SELECT playlist_has_tracks.track_id FROM spotitube.playlist_has_tracks WHERE playlist_has_tracks.playlist_id = ?)\n")
        ) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                tracks.add(
                        new VideoTrack(
                                resultSet.getInt("id"),
                                resultSet.getString("title"),
                                resultSet.getString("performer"),
                                resultSet.getInt("duration"),
                                resultSet.getInt("playcount"),
                                resultSet.getBoolean("offlineAvailable"),
                                resultSet.getString("publicationDate"),
                                resultSet.getString("description")
                        )
                );
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return tracks;
    }
}
