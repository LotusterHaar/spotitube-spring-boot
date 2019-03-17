package oose.dea.lotusterhaar.persistence;

import oose.dea.lotusterhaar.domain.*;

import javax.inject.Inject;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class PlaylistDAO {

    @Inject
    ConnectionFactory connectionFactory;

    @Inject
    TokenDAO tokenDAO;

    public Library getAllPlaylists(String token) {
        Library library = new Library();
        try (Connection connection = connectionFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     "SELECT \n" +
                             "    playlists_view.*, token.token\n" +
                             "FROM\n" +
                             "    playlists_view\n" +
                             "        LEFT JOIN\n" +
                             "    token ON playlists_view.username = token.account_user\n" +
                             "WHERE\n" +
                             "    token.token = ? \n");
        ) {
            statement.setString(1, token);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int playlist_id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                Boolean owner = resultSet.getBoolean("owner");
                library.getPlaylists().add(new Playlist(playlist_id, name, owner, new ArrayList<Track>()));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return library;
    }

    public int getTotalLengthFromAllPlaylists() {
        int totalLength = 0;
        try (
                Connection connection = connectionFactory.getConnection();
                PreparedStatement statement = connection.prepareStatement("");
        ) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                totalLength = resultSet.getInt("");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return totalLength;
    }

    public TrackOverview getAllTracksFromPlaylist(int playlistId) {
        TrackOverview trackOverview = new TrackOverview();

        TrackOverview songTrackOverview = getSongsTracksInPlaylist(playlistId);
        TrackOverview videoTrackOverview = getVideoTracksInPlaylist(playlistId);
        trackOverview.getTracks().addAll(songTrackOverview.getTracks());
        trackOverview.getTracks().addAll(videoTrackOverview.getTracks());

        return trackOverview;
    }

    public TrackOverview getSongsTracksInPlaylist(int playlistId) {
        TrackOverview trackOverview = new TrackOverview();
        try (Connection connection = connectionFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT \n" +
                     "    *\n" +
                     "FROM\n" +
                     "    spotitube.songtracks_view stv\n" +
                     "        LEFT JOIN\n" +
                     "    spotitube.playlist_has_tracks pht ON pht.track_id = stv.id\n" +
                     "        AND pht.playlist_id = ?\n" +
                     "WHERE\n" +
                     "    stv.id IN (SELECT \n" +
                     "            track_id\n" +
                     "        FROM\n" +
                     "            spotitube.playlist_has_tracks\n" +
                     "        WHERE\n" +
                     "            playlist_id = ?)");

        ) {
            statement.setInt(1, playlistId);
            statement.setInt(2, playlistId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int track_id = resultSet.getInt("id");
                String title = resultSet.getString("title");
                String performer = resultSet.getString("performer");
                int duration = resultSet.getInt("duration");
                int playcount = resultSet.getInt("playcount");
                Boolean offlineAvailable = resultSet.getBoolean("offlineAvailable");
                String album = resultSet.getString("album");
                trackOverview.addTrack(new SongTrack(track_id, title, performer, duration, playcount, offlineAvailable, album));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return trackOverview;
    }

    public TrackOverview getVideoTracksInPlaylist(int playlistId) {
        TrackOverview trackOverview = new TrackOverview();
        try (Connection connection = connectionFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT \n" +
                     "    *\n" +
                     "FROM\n" +
                     "    spotitube.videotracks_view vtv\n" +
                     "        LEFT JOIN\n" +
                     "    spotitube.playlist_has_tracks pht ON pht.track_id = vtv.id\n" +
                     "        AND pht.playlist_id = ?\n" +
                     "WHERE\n" +
                     "    vtv.id IN (SELECT \n" +
                     "            track_id\n" +
                     "        FROM\n" +
                     "            spotitube.playlist_has_tracks\n" +
                     "        WHERE\n" +
                     "            playlist_id = ?)\n");
        ) {
            statement.setInt(1, playlistId);
            statement.setInt(2, playlistId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int track_id = resultSet.getInt("id");
                String title = resultSet.getString("title");
                String performer = resultSet.getString("performer");
                int duration = resultSet.getInt("duration");
                int playcount = resultSet.getInt("playcount");
                Boolean offlineAvailable = resultSet.getBoolean("offlineAvailable");
                String publicationDate = resultSet.getString("publicationDate");
                String description = resultSet.getString("description");
                trackOverview.addTrack(new VideoTrack(track_id, title, performer, duration, playcount, offlineAvailable, publicationDate, description));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return trackOverview;
    }

    public void deletePlaylistById(int id) {
        try (
                Connection connection = connectionFactory.getConnection();
                PreparedStatement statement = connection.prepareStatement("DELETE FROM spotitube.playlist WHERE playlist.id = ?")
        ) {
            statement.setInt(1, id);
            statement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void addPlaylist(String token, Playlist playlist) {
        try (
                Connection connection = connectionFactory.getConnection();
                PreparedStatement getLibraryIdsStatement = connection.prepareStatement("SELECT library.id FROM spotitube.library WHERE library.username = ?");
                PreparedStatement createPlaylistStatement = connection.prepareStatement("INSERT INTO playlist (name, owner, library_id) VALUES (?,?,?)");
        ) {
            String username = tokenDAO.findUsernameByToken(token);
            getLibraryIdsStatement.setString(1, username);
            ResultSet resultSet = getLibraryIdsStatement.executeQuery();
            while (resultSet.next()) {
                int library_id = resultSet.getInt("id");
                createPlaylistStatement.setString(1, playlist.getName());
                createPlaylistStatement.setBoolean(2, true);
                createPlaylistStatement.setInt(3, library_id);
                createPlaylistStatement.execute();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public void updateNameOfPlaylist(int id, String name) {

        try (Connection connection = connectionFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement("UPDATE spotitube.playlist \n" +
                     "SET \n" +
                     "    playlist.name = ? \n" +
                     "WHERE\n" +
                     "    playlist.id = ?");
        ) {
            statement.setString(1, name);
            statement.setInt(2, id);
            statement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteTrackFromPlaylist(int playlistId, int trackId) {

        try (Connection connection = connectionFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement("DELETE FROM playlist_has_tracks WHERE playlist_id = ? AND track_id = ?");
        ) {
            statement.setInt(1, playlistId);
            statement.setInt(2, trackId);
            statement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void addTrackToPlaylist(int id, Track track) {
        System.out.println("track");
        System.out.println(track.toString());
        try (Connection connection = connectionFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement("INSERT INTO spotitube.playlist_has_tracks (playlist_id,track_id) VALUES (?,?)");
        ) {
            statement.setInt(1, id);
            statement.setInt(2, track.getId());
            statement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}

