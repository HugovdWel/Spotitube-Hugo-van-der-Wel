package HugoVanDerWel.Services;

import HugoVanDerWel.Models.UserModel;
import HugoVanDerWel.persistence.UserPersistence;
import HugoVanDerWel.services.AuthenticationService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.when;


public class AuthenticationServiceTest {
    private AuthenticationService sut;
    private UserPersistence userPersistenceMock;
    private final UserModel userModelUsernamePassword = new UserModel() {{
        username = "Giel";
        password = "Bankjespuree111";
    }};
    private final UserModel userModelUsernameToken = new UserModel(){{
        username = "Giel";
        password = "Bankjespuree111";
        token = "123123123";
    }};

    @Before
    public void setUp() {
        userPersistenceMock = Mockito.mock(UserPersistence.class);
        sut = new AuthenticationService(userPersistenceMock);
    }

    @Test
    public void verifyPasswordVerifiesCorrectPassword() {
        //Arrange
        when(userPersistenceMock.getPasswordForUser(Mockito.any(UserModel.class))).thenReturn(userModelUsernamePassword);

        //Act
        var a = sut.verifyPassword(userModelUsernamePassword);

        //Assert
        Assert.assertTrue(a);
    }

    @Test
    public void verifyPasswordRefusesIncorrectPassword() {
        when(userPersistenceMock.getPasswordForUser(Mockito.any(UserModel.class))).thenReturn(new UserModel() {{
            username = "Giel";
            password = "Bankjespuree222FOUT";
        }});

        Assert.assertFalse(sut.verifyPassword(userModelUsernamePassword));
    }

    @Test
    public void verifyTokenVerifiesCorrectToken() {
        when(userPersistenceMock.getTokenForUser(Mockito.any(UserModel.class))).thenReturn(userModelUsernameToken);

        Assert.assertTrue(sut.verifyToken(userModelUsernameToken));
    }

    @Test
    public void verifyTokenRefusesIncorrectToken() {
        when(userPersistenceMock.getTokenForUser(Mockito.any(UserModel.class))).thenReturn(new UserModel(){{
            username = "Giel";
            token = "token5";
        }});

        Assert.assertFalse(sut.verifyToken(userModelUsernameToken));
    }

    @Test
    public void generateNewTokenForUserGeneratesUniqueToken() {
        when(userPersistenceMock.setTokenForUser(Mockito.any(UserModel.class), Mockito.anyString())).thenAnswer(i -> new UserModel(){{
            username = "Doesn't matter";
            token = i.getArguments()[1].toString();
        }});

        Assert.assertNotEquals(sut.generateNewTokenForUser(userModelUsernamePassword).token, sut.generateNewTokenForUser(userModelUsernamePassword).token);
    }
}
