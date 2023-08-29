package HugoVanDerWel.Resources;

import HugoVanDerWel.DataTransferObjects.LoginRequestDTO;
import HugoVanDerWel.Models.UserModel;
import HugoVanDerWel.resources.LoginResource;
import HugoVanDerWel.services.AuthenticationService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.ws.rs.core.Response;

import org.mockito.Mockito;


public class LoginResourceTest {
    private LoginResource sut;
    private AuthenticationService authenticationServiceMock;
    private final UserModel userModelUsernamePassword = new UserModel() {{
        username = "Giel";
        password = "Bankjespuree111";
    }};
    private final UserModel userModelUsernameToken = new UserModel() {{
        username = "Giel";
        password = "Bankjespuree111";
        token = "123123123";
    }};

    @Before
    public void setUp() {
        authenticationServiceMock = Mockito.mock(AuthenticationService.class);
        sut = new LoginResource(authenticationServiceMock);
    }

    @Test
    public void goodLoginTest() {
        Mockito.when(authenticationServiceMock.verifyPassword(Mockito.any())).thenReturn(true);
        Mockito.when(authenticationServiceMock.generateNewTokenForUser(Mockito.any())).thenReturn(userModelUsernameToken);

        Response responseEntity = sut.login(new LoginRequestDTO(userModelUsernamePassword.username, userModelUsernamePassword.password));
        UserModel responseBody = (UserModel) responseEntity.getEntity();
        Assert.assertEquals(responseBody.username, "Giel");
        Assert.assertEquals(responseBody.token, "123123123");
    }

    @Test
    public void badLoginTest() {
        Mockito.when(authenticationServiceMock.verifyPassword(Mockito.any())).thenReturn(false);

        Response responseEntity = sut.login(new LoginRequestDTO(userModelUsernamePassword.username, userModelUsernamePassword.password));
        int responseStatus = responseEntity.getStatus();
        Assert.assertEquals(responseStatus, 304);
    }
}
