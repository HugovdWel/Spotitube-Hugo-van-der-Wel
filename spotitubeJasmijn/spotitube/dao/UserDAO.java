package nl.han.danielvervloed.oose.spotitube.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.inject.Inject;
import nl.han.danielvervloed.oose.spotitube.dao.exception.QueryFailure;


public class UserDAO {
	private DBConnection connection;
	private PreparedStatement query;
	private ResultSet results;

	public String getPassword(String user) {
		try {
			Connection SQL = connection.connect();
			query = SQL.prepareStatement("SELECT userhash FROM User WHERE username = ?");
			query.setString(1, user);
			results = query.executeQuery();
			results.next();
			return results.getString("userhash");
		} catch (SQLException e) {
			throw new QueryFailure("Query to find username in table User failed!");
		}
	}

	public boolean checkTokenNotUnique(String token) {
		try {
			Connection SQL = connection.connect();
			query = SQL.prepareStatement("SELECT username FROM User WHERE token = ?");
			query.setString(1, token);
			ResultSet results = query.executeQuery();
			return (results.next());
		} catch (SQLException e) {
			throw new QueryFailure("Query to find a duplicate token in table User failed!");
		}
	}

	public void setUsersToken(String user, String token) {
		try {
			Connection SQL = connection.connect();
			query = SQL.prepareStatement("UPDATE User SET token = ? WHERE username = ?");
			query.setString(1, token);
			query.setString(2, user);
			query.executeUpdate();
		} catch (SQLException e) {
			throw new QueryFailure("Query to add a token to a user in table User failed");
		}
	}

	public String getUserFromToken(String token) {
		try {
			Connection SQL = connection.connect();
			query = SQL.prepareStatement("SELECT username FROM User WHERE token = ?");
			query.setString(1, token);
			results = query.executeQuery();
			results.next();
			return results.getString("username");
		} catch (SQLException e) {
			throw new QueryFailure("Query to find a user from there token in table User failed!");
		}
	}

	@Inject
	public void setConnection(DBConnection connection) {
		this.connection = connection;
	}

	public ResultSet getResults() {
		return results;
	}


}
