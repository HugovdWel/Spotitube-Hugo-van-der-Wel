package nl.han.danielvervloed.oose.spotitube.dao;


import nl.han.danielvervloed.oose.spotitube.dao.exception.DatabaseConnectionFail;


import java.sql.*;
import java.util.Properties;


public class DBConnection {

    private Properties properties = new Properties();

    private Connection cnEmps = null;

    public Connection connect() {
        try {
            properties.load(DBConnection.class.getClassLoader().getResourceAsStream("database.properties"));
            Class.forName(properties.getProperty("driver"));
            cnEmps = DriverManager.getConnection(properties.getProperty("connectionString"));
            return cnEmps;
        } catch (Exception e) {
            throw new DatabaseConnectionFail();

        }
    }
}