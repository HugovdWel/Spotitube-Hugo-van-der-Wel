package service;

import java.util.ArrayList;
import java.util.List;
import nl.han.danielvervloed.oose.spotitube.dao.TracksDAO;
import nl.han.danielvervloed.oose.spotitube.dto.PlaylistDTO;
import nl.han.danielvervloed.oose.spotitube.dto.PlaylistsDTO;
import nl.han.danielvervloed.oose.spotitube.dto.TrackDTO;
import nl.han.danielvervloed.oose.spotitube.dto.TracksDTO;
import nl.han.danielvervloed.oose.spotitube.service.TracksService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class TracksServiceTest {
	private TracksService sut;
	private TracksDAO tracksDAOTest;

	@BeforeEach
	void setup() {
		sut = new TracksService();
		sut.setTracksDAO(Mockito.mock(TracksDAO.class));
		tracksDAOTest = sut.getTracksDAO();
	}



	@Test
	void getAllAvailableTracksHappyPath(){
		// Arrange
		int input = 1;
		TracksDTO expected = new TracksDTO();
		List<TrackDTO> trackDTOTest = new ArrayList<>();
		TrackDTO testFiller = new TrackDTO();
		testFiller.setId(1);
		testFiller.setTitle("Title");
		testFiller.setAlbum("Album");
		testFiller.setPerformer("Performer");
		testFiller.setOfflineAvailable(true);
		testFiller.setPublicationDate("1-1-1");
		testFiller.setDuration(1);
		testFiller.setDescription("Description");
		testFiller.setPlaycount(1);

		trackDTOTest.add(testFiller);
		expected.setTracks(trackDTOTest);
		Mockito.when(tracksDAOTest.getAllAvailable(Mockito.anyInt())).thenReturn(trackDTOTest);

		// Act
		TracksDTO result = sut.getAllAvailableTracks(input);

		// Assert
		Assertions.assertEquals(expected.getTracks(), result.getTracks());
	}

	@Test
	void getAllTracksFromAPlaylistHappyPath(){
		// Arrange
		int input = 1;
		TracksDTO expected = new TracksDTO();
		List<TrackDTO> trackDTOTest = new ArrayList<>();
		TrackDTO testFiller = new TrackDTO();
		testFiller.setId(1);
		testFiller.setTitle("Title");
		testFiller.setAlbum("Album");
		testFiller.setPerformer("Performer");
		testFiller.setOfflineAvailable(true);
		testFiller.setPublicationDate("1-1-1");
		testFiller.setDuration(1);
		testFiller.setDescription("Description");
		testFiller.setPlaycount(1);

		trackDTOTest.add(testFiller);
		expected.setTracks(trackDTOTest);
		Mockito.when(tracksDAOTest.getAllTracksFromAPlaylist(Mockito.anyInt())).thenReturn(trackDTOTest);

		// Act
		TracksDTO result = sut.getAllTracksFromAPlaylist(input);

		// Assert
		Assertions.assertEquals(expected.getTracks(), result.getTracks());
	}

	@Test
	void deleteTrackFromPlaylistHappyPath(){
		// Arrange
		int inputOne = 1;
		int inputTwo = 1;

		// Act
		sut.deleteTrackFromPlaylist(inputOne, inputTwo);

		// Assert
		Mockito.verify(tracksDAOTest, Mockito.times(1)).deleteTrackFromPlaylist(inputOne, inputTwo);
	}

	@Test
	void addTrackToPlaylistHappyPath(){
		// Arrange
		TrackDTO inputOne = new TrackDTO();
		inputOne.setId(1);
		inputOne.setTitle("Title");
		inputOne.setAlbum("Album");
		inputOne.setPerformer("Performer");
		inputOne.setOfflineAvailable(true);
		inputOne.setPublicationDate("1-1-1");
		inputOne.setDuration(1);
		inputOne.setDescription("Description");
		inputOne.setPlaycount(1);
		int inputTwo = 1;

		// Act
		sut.addTrackToPlaylist(inputOne, inputTwo);

		// Assert
		Mockito.verify(tracksDAOTest, Mockito.times(1)).addTrackToPlaylist(inputOne, inputTwo);
	}


}
