package recources;

import javax.ws.rs.core.Response;
import nl.han.danielvervloed.oose.spotitube.resources.TracksResource;
import nl.han.danielvervloed.oose.spotitube.service.LoginService;
import nl.han.danielvervloed.oose.spotitube.service.TracksService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class TracksResourceTest {
	private TracksResource sut;
	final int RESPONSE_STATUS_OK = 200;
	final String TEST_TOKEN = "111111";
	final int TEST_ID = 1;

	@BeforeEach
	void setup() {
		sut = new TracksResource();
		sut.setLoginService(Mockito.mock(LoginService.class));
		sut.setTracksService(Mockito.mock(TracksService.class));
	}

	@Test
	void getAllAvailableTracksHappyPath(){
		// Arrange

		// Act
		Response result = sut.getAllAvailableTracks(TEST_TOKEN, TEST_ID);

		// Assert
		Assertions.assertEquals(RESPONSE_STATUS_OK, result.getStatus());
	}
}
