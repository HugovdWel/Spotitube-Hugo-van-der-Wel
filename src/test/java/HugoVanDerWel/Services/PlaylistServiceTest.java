package HugoVanDerWel.Services;

import HugoVanDerWel.Models.PlaylistModel;
import HugoVanDerWel.persistence.PlaylistPersistence;
import HugoVanDerWel.services.Database;
import org.junit.Test;

import java.sql.DriverManager;
import java.sql.SQLException;

public class PlaylistServiceTest {

    @Test
    public void test(){
        PlaylistPersistence pl = new PlaylistPersistence(new Database());
        pl.createPlaylist(new PlaylistModel(){{name = "appel"; ownerName = "gert";}});
        pl.deletePlaylist(3);
//
//        try{
//
////        var a = DriverManager.getConnection("jdbc:sqlserver://localhost", "Spotitube", "AppelTaart");
//        var a = DriverManager.getConnection(connectionURL);
//        var b = a.prepareStatement("SELECT 1 FROM spotitubeUser");
//        var c = b.executeQuery();
//        System.out.println(c);
//        } catch (SQLException e){
//            System.out.println(e);
//        }
    }
}
