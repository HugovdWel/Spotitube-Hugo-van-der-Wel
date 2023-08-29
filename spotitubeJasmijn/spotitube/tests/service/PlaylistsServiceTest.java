package service;

import java.util.ArrayList;
import java.util.List;
import nl.han.danielvervloed.oose.spotitube.dao.PlaylistsDAO;
import nl.han.danielvervloed.oose.spotitube.dto.PlaylistDTO;
import nl.han.danielvervloed.oose.spotitube.dto.PlaylistsDTO;
import nl.han.danielvervloed.oose.spotitube.service.PlaylistsService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class PlaylistsServiceTest {
	private PlaylistsService sut;
	private PlaylistsDAO playlistsDAOTest;

	@BeforeEach
	void setup() {
		sut = new PlaylistsService();
		sut.setPlaylistsDAO(Mockito.mock(PlaylistsDAO.class));
		playlistsDAOTest = sut.getPlayListsDao();
	}

	@Test
	void getAllPlaylistsHappyPath(){
		// Arrange
		String input = "testUser";
		PlaylistsDTO expected = new PlaylistsDTO();
		List<PlaylistDTO> playlistDTOTest = new ArrayList<>();
		PlaylistDTO playListResult = new PlaylistDTO();
		playListResult.setOwner(true);
		playListResult.setName("TestName");
		playListResult.setId(1);
		playlistDTOTest.add(playListResult);
		expected.setPlaylists(playlistDTOTest);
		expected.setLength(1);
		Mockito.when(playlistsDAOTest.getAll(Mockito.anyString())).thenReturn(playlistDTOTest);

		// Act
		PlaylistsDTO result = sut.getAllPlaylists(input);

		// Assert
		Assertions.assertEquals(expected.getPlaylists(), result.getPlaylists());
	}

	@Test
	void deletePlaylistHappyPath(){
		// Arrange
		int input = 1;

		// Act
		sut.deletePlaylist(input);

		// Assert
		Mockito.verify(playlistsDAOTest, Mockito.times(1)).deleteOne(input);
	}

	@Test
	void addPlaylistHappyPath(){
		// Arrange
		PlaylistDTO inputOne = new PlaylistDTO();
		inputOne.setOwner(true);
		inputOne.setName("TestName");
		inputOne.setId(1);
		String inputTwo = "TestUsername";

		// Act
		sut.addPlaylist(inputOne, inputTwo);

		// Assert
		Mockito.verify(playlistsDAOTest, Mockito.times(1)).addOne(inputOne.getName(), inputTwo);
	}

	@Test
	void editPlaylistHappyPath(){
		// Arrange
		String inputOne = "newName";
		int inputTwo = 1;

		// Act
		sut.editPlaylist(inputOne, inputTwo);

		// Assert
		Mockito.verify(playlistsDAOTest, Mockito.times(1)).editOne(inputOne, inputTwo);
	}

}
