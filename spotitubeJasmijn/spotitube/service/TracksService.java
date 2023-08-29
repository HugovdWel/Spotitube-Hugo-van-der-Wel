package nl.han.danielvervloed.oose.spotitube.service;

import javax.inject.Inject;
import nl.han.danielvervloed.oose.spotitube.dao.TracksDAO;
import nl.han.danielvervloed.oose.spotitube.dto.TrackDTO;
import nl.han.danielvervloed.oose.spotitube.dto.TracksDTO;

public class TracksService {
	private TracksDAO tracksDAO;

	public TracksDTO getAllAvailableTracks(int playlistId) {
		TracksDTO tracksDTO = new TracksDTO();
		tracksDTO.setTracks(tracksDAO.getAllAvailable(playlistId));
		return tracksDTO;
	}

	public TracksDTO getAllTracksFromAPlaylist(int playlistId) {
		TracksDTO tracksDTO = new TracksDTO();
		tracksDTO.setTracks(tracksDAO.getAllTracksFromAPlaylist(playlistId));
		return tracksDTO;
	}

	public void deleteTrackFromPlaylist(int playlistId, int trackId) {
		tracksDAO.deleteTrackFromPlaylist(playlistId, trackId);
	}

	public void addTrackToPlaylist(TrackDTO addedTrack, int playlistId) {
		tracksDAO.addTrackToPlaylist(addedTrack, playlistId);
	}

	@Inject
	public void setTracksDAO(TracksDAO tracksDAO) {
		this.tracksDAO = tracksDAO;
	}

	public TracksDAO getTracksDAO(){
		return tracksDAO;
	}

}
