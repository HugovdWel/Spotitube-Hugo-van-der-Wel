package nl.han.danielvervloed.oose.spotitube.dao;

import nl.han.danielvervloed.oose.spotitube.dao.exception.QueryFailure;
import nl.han.danielvervloed.oose.spotitube.dto.PlaylistDTO;

import javax.inject.Inject;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PlaylistsDAO {
    private DBConnection connection;

    public List<PlaylistDTO> getAll(String user){
        try {
            Connection SQL = connection.connect();
            List<PlaylistDTO> playlists = new ArrayList<>();
            PreparedStatement query = SQL.prepareStatement("SELECT * FROM Playlist");
            ResultSet results = query.executeQuery();
            while(results.next()){
                PlaylistDTO playListResult = new PlaylistDTO();
                playListResult.setOwner(results.getString("username").equals(user));
                playListResult.setName(results.getString("playlistname"));
                playListResult.setId(results.getInt("playlistId"));
                playlists.add(playListResult);
            }
            return playlists;
        }
        catch(SQLException e){
            throw new QueryFailure("Query to find all playlists from a user in table Playlists failed");
        }
    }

    public void deleteOne(int id){
        try {
            Connection SQL = connection.connect();
            List<PlaylistDTO> playlists = new ArrayList<PlaylistDTO>();
            PreparedStatement query = SQL.prepareStatement("DELETE FROM Playlist WHERE playlistId = ?");
            query.setInt(1, id);
            query.executeUpdate();
        }
        catch(SQLException e){
            throw new QueryFailure("Query to delete a playlist from table Playlist failed");
        }
    }

    public void addOne(String name, String owner){
        try {
            Connection SQL = connection.connect();
            List<PlaylistDTO> playlists = new ArrayList<PlaylistDTO>();
            PreparedStatement query = SQL.prepareStatement("INSERT INTO Playlist (playlistname, username) VALUES (?, ?)");
            query.setString(1, name);
            query.setString(2, owner);
            query.executeUpdate();
        }
        catch(SQLException e){
            throw new QueryFailure("Query to add a playlist to table Playlist failed");
        }
    }

    public void editOne(String newName, int id) {
        try {
            Connection SQL = connection.connect();
            List<PlaylistDTO> playlists = new ArrayList<PlaylistDTO>();
            PreparedStatement query = SQL.prepareStatement("UPDATE Playlist SET playlistName = ? WHERE playlistId = ?");
            query.setString(1, newName);
            query.setInt(2, id);
            query.executeUpdate();
        }
        catch(SQLException e){
            throw new QueryFailure("Query to edit a playlist from table Playlist failed");
        }
    }

    public int getTotalDuration() {
        try {
            Connection SQL = connection.connect();
            PreparedStatement query = SQL.prepareStatement("SELECT SUM(DURATION) totalDuration FROM Track t Left join Track_in_playlist p ON t.trackId = p.trackId");
            ResultSet results = query.executeQuery();
            results.next();
            return results.getInt("totalDuration");
        } catch (SQLException e) {
            throw new QueryFailure("Query to get totalDuration from table Track & Track_in_playlist failed!");
        }
    }

    @Inject
    public void setConnection(DBConnection connection) {
        this.connection = connection;
    }

}
