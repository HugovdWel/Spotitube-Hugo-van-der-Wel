package recources;

import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.core.Response;
import nl.han.danielvervloed.oose.spotitube.dto.PlaylistDTO;
import nl.han.danielvervloed.oose.spotitube.dto.TrackDTO;
import nl.han.danielvervloed.oose.spotitube.resources.PlaylistsResource;
import nl.han.danielvervloed.oose.spotitube.service.LoginService;
import nl.han.danielvervloed.oose.spotitube.service.PlaylistsService;
import nl.han.danielvervloed.oose.spotitube.service.TracksService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class PlaylistsResourceTest {
	private PlaylistsResource sut;
	final int RESPONSE_STATUS_OK = 200;
	final String TEST_TOKEN = "111111";
	final int TEST_ID = 1;
	private TrackDTO testTrackDTO;
	private PlaylistDTO testPlaylistDTO;
	//PlaylistsDTO testPlaylistsDTO = new PlaylistsDTO();


	@BeforeEach
	void setup() {
		sut = new PlaylistsResource();
		testTrackDTO = new TrackDTO();
		testPlaylistDTO = new PlaylistDTO();

		//1.for when you a expect a DTO

		//1.1 TrackDTO
		testTrackDTO.setId(1);
		testTrackDTO.setTitle("testTrack");
		testTrackDTO.setOfflineAvailable(true);
		testTrackDTO.setAlbum("album");
		testTrackDTO.setDescription("");
		testTrackDTO.setDuration(1);
		testTrackDTO.setPerformer("performer");
		testTrackDTO.setPlaycount(1);
		testTrackDTO.setPublicationDate("1-1-1");

		//1.2 PlaylistDTO
		testPlaylistDTO.setId(1);
		testPlaylistDTO.setName("testPlaylist");
		testPlaylistDTO.setOwner(true);
		List<TrackDTO> testTracks = new ArrayList<>();
		testTracks.add(testTrackDTO);
		testPlaylistDTO.setTracks(testTracks);
/*
		//1.3 PlaylistsDTO
		testPlaylistsDTO.setLength(1);
		List<PlaylistDTO> testPlaylists = new ArrayList<>();
		testPlaylists.add(testPlaylistDTO);
		testPlaylistsDTO.setPlaylists(testPlaylists);
 */
		sut.setLoginService(Mockito.mock(LoginService.class));
		sut.setPlaylistsDTO(Mockito.mock(PlaylistsService.class));
		sut.setTracksService(Mockito.mock(TracksService.class));
	}

	@Test
	void getAllPlaylistsHappyPath(){
		// Arrange
		//PlaylistsService mockedPlaylistsService = Mockito.mock(PlaylistsService.class);
		//Mockito.when(mockedPlaylistsService.getAllPlaylists("")).thenReturn(testPlaylistsDTO);

		// Act
		Response result = sut.getAllPlaylists(TEST_TOKEN);
		//PlaylistsDTO actualOutput = (PlaylistsDTO) result.getEntity();

		// Assert
		Assertions.assertEquals(RESPONSE_STATUS_OK, result.getStatus());
		//Assertions.assertSame(testPlaylistDTO, actualOutput.getPlaylists());
	}

	@Test
	void deletePlaylistHappyPath(){
		// Arrange

		// Act
		Response result = sut.deletePlaylist(TEST_TOKEN, TEST_ID);

		// Assert
		Assertions.assertEquals(RESPONSE_STATUS_OK, result.getStatus());
	}

	@Test
	void addPlaylistHappyPath(){
		// Arrange

		// Act
		Response result = sut.addPlaylist(TEST_TOKEN, testPlaylistDTO);

		// Assert
		Assertions.assertEquals(RESPONSE_STATUS_OK, result.getStatus());
	}

	@Test
	void editPlaylistHappyPath(){
		// Arrange

		// Act
		Response result = sut.editPlaylist(TEST_TOKEN, TEST_ID, testPlaylistDTO);

		// Assert
		Assertions.assertEquals(RESPONSE_STATUS_OK, result.getStatus());
	}

	@Test
	void getAllTracksFromAPlaylistHappyPath(){
		// Arrange

		// Act
		Response result = sut.getAllTracksFromAPlaylist(TEST_TOKEN, TEST_ID);

		// Assert
		Assertions.assertEquals(RESPONSE_STATUS_OK, result.getStatus());
	}

	@Test
	void deleteTrackFromPlaylistHappyPath(){
		// Arrange

		// Act
		Response result = sut.deleteTrackFromPlaylist(TEST_TOKEN, TEST_ID, TEST_ID);

		// Assert
		Assertions.assertEquals(RESPONSE_STATUS_OK, result.getStatus());
	}

	@Test
	void addTrackToPlaylistHappyPath(){
		// Arrange

		// Act
		Response result = sut.addTrackToPlaylist(TEST_TOKEN, TEST_ID, testTrackDTO);

		// Assert
		Assertions.assertEquals(RESPONSE_STATUS_OK, result.getStatus());
	}

}
