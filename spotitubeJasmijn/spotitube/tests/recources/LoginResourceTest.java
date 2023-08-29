package recources;

import nl.han.danielvervloed.oose.spotitube.dto.LoginRequestDTO;
import nl.han.danielvervloed.oose.spotitube.dto.LoginResponseDTO;
import nl.han.danielvervloed.oose.spotitube.resources.LoginResource;
import nl.han.danielvervloed.oose.spotitube.service.LoginService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import javax.ws.rs.core.Response;
import org.mockito.Mockito;


public class LoginResourceTest {
    private LoginResource sut;
    final int RESPONSE_STATUS_OK = 200;

    @BeforeEach
    void setup() {
        sut = new LoginResource();
    }

    @Test
    void loginHappyPath() {
        // Arrange
        sut.setLoginService(Mockito.mock(LoginService.class));
        LoginRequestDTO input = new LoginRequestDTO();
        input.setUser("daniel");
        input.setPassword("daniel");
        String expectedOutputName = "daniel";

        // Act
        Response result = sut.login(input);
        LoginResponseDTO actualOutput = (LoginResponseDTO) result.getEntity();

        // Assert
		Assertions.assertEquals(expectedOutputName, actualOutput.getUser());
        Assertions.assertEquals(RESPONSE_STATUS_OK, result.getStatus());
    }

    //TODO tests voor tokengenerate
}
