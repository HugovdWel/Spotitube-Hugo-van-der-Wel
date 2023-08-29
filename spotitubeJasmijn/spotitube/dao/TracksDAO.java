package nl.han.danielvervloed.oose.spotitube.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import nl.han.danielvervloed.oose.spotitube.dao.exception.QueryFailure;
import nl.han.danielvervloed.oose.spotitube.dto.PlaylistDTO;
import nl.han.danielvervloed.oose.spotitube.dto.TrackDTO;

public class TracksDAO {
	private DBConnection connection;

	public List<TrackDTO> getAllAvailable(int playlistId) {
		try {
			Connection SQL = connection.connect();
			List<TrackDTO> tracks = new ArrayList<>();
			PreparedStatement query = SQL.prepareStatement("SELECT * FROM Track WHERE trackId NOT IN (SELECT trackId FROM Track_in_playlist where playlistId = ?)");
			query.setInt(1, playlistId);
			ResultSet results = query.executeQuery();
			while (results.next()) {
				TrackDTO trackResult = new TrackDTO();
				tracks.add(setTrackFromQueryResults(results, trackResult));
			}
			return tracks;
		} catch (SQLException e) {
			throw new QueryFailure("Query to find all available tracks for a playlist in table Tracks failed");
		}
	}


	public List<TrackDTO> getAllTracksFromAPlaylist(int playlistId) {
		try {
			Connection SQL = connection.connect();
			List<TrackDTO> tracks = new ArrayList<>();
			PreparedStatement query = SQL.prepareStatement("SELECT * FROM Track t left join Track_in_playlist p ON t.trackId = p.trackId WHERE playlistId = ?");
			query.setInt(1, playlistId);
			ResultSet results = query.executeQuery();
			while (results.next()) {
				TrackDTO trackResult = new TrackDTO();
				trackResult.setOfflineAvailable(results.getBoolean("offlineavailable"));
				tracks.add(setTrackFromQueryResults(results, trackResult));
			}

			return tracks;
		} catch (SQLException e) {
			throw new QueryFailure("Query to find all tracks from a playlist in table Track_in_playlist failed");
		}
	}

	public void deleteTrackFromPlaylist(int playlistId, int trackId){
		try {
			Connection SQL = connection.connect();
			List<PlaylistDTO> playlists = new ArrayList<>();
			PreparedStatement query = SQL.prepareStatement("DELETE FROM Track_in_playlist WHERE playlistId = ? AND trackId = ?");
			query.setInt(1, playlistId);
			query.setInt(2, trackId);
			query.executeUpdate();
		}
		catch(SQLException e){
			throw new QueryFailure("Query to delete a track from a playlist in table Track_in_playlist failed");
		}
	}

	public void addTrackToPlaylist(TrackDTO addedTrack, int playlistId) {
		try {
			Connection SQL = connection.connect();
			List<PlaylistDTO> playlists = new ArrayList<>();
			PreparedStatement query = SQL.prepareStatement("INSERT INTO Track_in_playlist (playlistId, trackId, offlineAvailable, playCount) VALUES (?, ?, ?, ?)");
			query.setInt(1, playlistId);
			query.setInt(2, addedTrack.getId());
			query.setBoolean(3, addedTrack.isOfflineAvailable());
			query.setInt(4, addedTrack.getPlaycount());
			query.executeUpdate();
		}
		catch(SQLException e){
			throw new QueryFailure("Query to add a track to a playlist in table Track_in_playlist failed");
		}
	}


	private TrackDTO setTrackFromQueryResults(ResultSet results, TrackDTO trackResult) throws SQLException {
		trackResult.setId(results.getInt("trackid"));
		trackResult.setTitle(results.getString("title"));
		trackResult.setPerformer(results.getString("performer"));
		trackResult.setDuration(results.getInt("duration"));
		trackResult.setAlbum(results.getString("album"));
		trackResult.setPublicationDate(results.getString("publicationdate"));
		trackResult.setDescription(results.getString("description"));
		return trackResult;

	}

	@Inject
	public void setConnection(DBConnection connection) {
		this.connection = connection;
	}

}
