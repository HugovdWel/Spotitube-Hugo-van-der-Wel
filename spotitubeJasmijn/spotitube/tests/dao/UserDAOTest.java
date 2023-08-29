package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import nl.han.danielvervloed.oose.spotitube.dao.DBConnection;
import nl.han.danielvervloed.oose.spotitube.dao.UserDAO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class UserDAOTest {
	private UserDAO sut;
	private ResultSet resultSetTest;

	@BeforeEach
	void setup() {
		sut = new UserDAO();
		sut.setConnection(Mockito.mock(DBConnection.class));
		Connection SQLTest = Mockito.mock(Connection.class);
		resultSetTest = sut.getResults();

	}
/*
	@Test
	void getPasswordHappyPath() throws SQLException {
		// Arrange
		String input = "testUser";
		String expected = "testPassword";
		ResultSet woop = Mockito.mock(ResultSet.class);
		Mockito.when
		Mockito.when(woop.getString(Mockito.anyString())).thenReturn(expected);

		// Act
		String result = sut.getPassword(input);

		// Assert
		Assertions.assertEquals(expected, result);
	}
*/
}
