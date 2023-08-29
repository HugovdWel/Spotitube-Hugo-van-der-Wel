package service;

import nl.han.danielvervloed.oose.spotitube.dao.UserDAO;
import nl.han.danielvervloed.oose.spotitube.dto.LoginRequestDTO;
import nl.han.danielvervloed.oose.spotitube.service.LoginService;
import nl.han.danielvervloed.oose.spotitube.service.exception.LoginIncorrect;
import nl.han.danielvervloed.oose.spotitube.service.exception.TokenIncorrect;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class LoginServiceTest {
	private LoginService sut;
	private UserDAO userDAOTest;

	@BeforeEach
	void setup() {
		sut = new LoginService();
		sut.setUserDAO(Mockito.mock(UserDAO.class));
		userDAOTest = sut.getUserDAO();
	}

	@Test
	void checkLoginHappyPath(){
		// Arrange
		LoginRequestDTO input = new LoginRequestDTO();
		input.setPassword("password");
		input.setUser("user");
		input.setToken(-1);
		Mockito.when(userDAOTest.getPassword(Mockito.anyString())).thenReturn("password");

		// Act
		sut.checkLogin(input);

		// Assert
		Mockito.verify(userDAOTest, Mockito.times(1)).getPassword(input.getUser());
	}

	@Test
	void checkLoginErrorPath(){
		// Arrange
		LoginRequestDTO input = new LoginRequestDTO();
		input.setPassword("notPassword");
		input.setUser("user");
		input.setToken(-1);
		Mockito.when(userDAOTest.getPassword(Mockito.anyString())).thenReturn("password");

		// Act & Assert
		Assertions.assertThrows(LoginIncorrect.class, () -> {
			sut.checkLogin(input);
		});
	}

	@Test
	void checkTokenHappyPath(){
		// Arrange
		String input = "111111";
		Mockito.when(userDAOTest.checkTokenNotUnique(Mockito.anyString())).thenReturn(true);

		// Act
		sut.checkToken(input);

		// Assert
		Mockito.verify(userDAOTest, Mockito.times(1)).checkTokenNotUnique(input);
	}

	@Test
	void checkTokenErrorPath(){
		// Arrange
		String input = "111111";
		Mockito.when(userDAOTest.checkTokenNotUnique(Mockito.anyString())).thenReturn(false);

		// Act & Assert
		Assertions.assertThrows(TokenIncorrect.class, () -> {
			sut.checkToken(input);
			});
	}

	@Test
	void setUsersTokenHappyPath(){
		// Arrange
		String inputOne = "user";
		String inputTwo = "111111";

		// Act
		sut.setUsersToken(inputOne, inputTwo);

		// Assert
		Mockito.verify(userDAOTest, Mockito.times(1)).setUsersToken(inputOne, inputTwo);
	}

	@Test
	void checkTokenNotUniqueHappyPath(){
		// Arrange
		String input = "111111";
		Mockito.when(userDAOTest.checkTokenNotUnique(Mockito.anyString())).thenReturn(false);

		// Act
		Boolean result = sut.checkTokenNotUnique(input);

		// Assert
		Assertions.assertFalse(result);
	}

	@Test
	void getUserFromTokenHappyPath(){
		// Arrange
		String input = "111111";
		Mockito.when(userDAOTest.getUserFromToken(Mockito.anyString())).thenReturn("testUser");
		String expected = "testUser";

		// Act
		String result = sut.getUserFromToken(input);

		// Assert
		Assertions.assertEquals(expected, result);
	}

}
