package com.oose.dea;

import com.oose.dea.dao.PlaylistDAO;
import com.oose.dea.domain.Playlist;
import com.oose.dea.domain.Track;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.sql.DataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.*;

public class PlaylistDAOTest {

        PlaylistDAO playlistDAO;
        DataSource dataSource;
        Connection connection;
        PreparedStatement preparedStatement;
        ResultSet resultSet;

        @BeforeEach
        public void setup(){
            playlistDAO = new PlaylistDAO();
            dataSource = mock(DataSource.class);
            connection = mock(Connection.class);
            preparedStatement = mock(PreparedStatement.class);
            resultSet = mock(ResultSet.class);
        }

    @Test
    public void getPlaylistByIdTest(){
        try {
            String expectedSQL = "select * from playlist where id = ?";

            when(dataSource.getConnection()).thenReturn(connection);
            when(connection.prepareStatement(expectedSQL)).thenReturn(preparedStatement);
            when(preparedStatement.executeQuery()).thenReturn(resultSet);
            when(resultSet.next()).thenReturn(false);

            playlistDAO.setDataSource(dataSource);
            int playlistId = 1;
            Playlist playlist = playlistDAO.getPlaylistById(playlistId, "token");

            verify(dataSource).getConnection();
            verify(connection).prepareStatement(expectedSQL);
            verify(preparedStatement).setInt(1, playlistId);
            verify(preparedStatement).executeQuery();

        } catch (Exception e){
            fail(e.getMessage());
        }
    }

    @Test
    public void getPlaylistsTest(){
        try {
            String expectedSQL = "select * from playlist";

            when(dataSource.getConnection()).thenReturn(connection);
            when(connection.prepareStatement(expectedSQL)).thenReturn(preparedStatement);
            when(preparedStatement.executeQuery()).thenReturn(resultSet);
            when(resultSet.next()).thenReturn(false);

            playlistDAO.setDataSource(dataSource);
            ArrayList<Playlist> playlists = playlistDAO.getPlaylists("token");

            verify(dataSource).getConnection();
            verify(connection).prepareStatement(expectedSQL);
            verify(preparedStatement).executeQuery();

        } catch (Exception e){
            fail(e.getMessage());
        }
    }

//    //TODO
//    @Test
//    public void deletePlaylistTest(){
//        try {
//            String expectedSQL = "delete from playlist where id = ?";
//
//            when(dataSource.getConnection()).thenReturn(connection);
//            when(connection.prepareStatement(expectedSQL)).thenReturn(preparedStatement);
//            when(preparedStatement.executeQuery()).thenReturn(resultSet);
//            when(resultSet.next()).thenReturn(false);
//
//            playlistDAO.setDataSource(dataSource);
//            int playlistId = 1;
//            ArrayList<Playlist> playlists = playlistDAO.deletePlaylistById(playlistId, "token");
//
////            verify(dataSource).getConnection();
////            verify(connection).prepareStatement(expectedSQL);
////            verify(preparedStatement).setInt(1, playlistId);
////            verify(preparedStatement).executeQuery();
//
//        } catch (Exception e){
//            fail(e.getMessage());
//        }
//    }
//
//    @Test
//    public void updatePlaylistByIdTest(){
//
//    }

    @Test
    public void getTracksByPlaylistIdTest(){
        try {
            String expectedSQL = "select t.* from playlisttracks pt inner join track t on pt.trackId = t.id where pt.playlistId = ?";

            when(dataSource.getConnection()).thenReturn(connection);
            when(connection.prepareStatement(expectedSQL)).thenReturn(preparedStatement);
            when(preparedStatement.executeQuery()).thenReturn(resultSet);
            when(resultSet.next()).thenReturn(false);

            playlistDAO.setDataSource(dataSource);
            int playlistId = 1;
            ArrayList<Track> tracks = playlistDAO.getTracksByPlaylistId(playlistId, "token");

            verify(dataSource).getConnection();
            verify(connection).prepareStatement(expectedSQL);
            verify(preparedStatement).executeQuery();

        } catch (Exception e){
            fail(e.getMessage());
        }
    }

    @Test
    public void deleteSongFromPlaylistTest(){

    }

    @Test
    public void getAllTracksTest(){
        try {
            String expectedSQL = "select * from track";

            when(dataSource.getConnection()).thenReturn(connection);
            when(connection.prepareStatement(expectedSQL)).thenReturn(preparedStatement);
            when(preparedStatement.executeQuery()).thenReturn(resultSet);
            when(resultSet.next()).thenReturn(false);

            playlistDAO.setDataSource(dataSource);
            ArrayList<Track> tracks = playlistDAO.getAllTracks("token");

            verify(dataSource).getConnection();
            verify(connection).prepareStatement(expectedSQL);
            verify(preparedStatement).executeQuery();

        } catch (Exception e){
            fail(e.getMessage());
        }
    }

//    @Test
//    public void addTrackToPlaylistTest(){
//
//    }

//    @Test
//    public void addPlaylistTest(){
//        try {
//
//            String expectedSQL = "insert into playlist(name, owner) values(?, ?)";
//
//            when(dataSource.getConnection()).thenReturn(connection);
//            when(connection.prepareStatement(expectedSQL)).thenReturn(preparedStatement);
//            when(preparedStatement.executeUpdate()).thenReturn(1);
////            when(resultSet.next()).thenReturn(false);
//
//            playlistDAO.setDataSource(dataSource);
//
//            ArrayList<Playlist> playlists = playlistDAO.addPlaylist("name", "token");
//
//            verify(dataSource).getConnection();
//            verify(connection).prepareStatement(expectedSQL);
//            verify(preparedStatement).setString(1, "name");
//            verify(preparedStatement).setString(2, "owner");
//
//            verify(preparedStatement).executeUpdate();
//
//        } catch (Exception e){
//            fail(e.getMessage());
//        }
//    }

    @Test
    public void getTotalDurationTest(){

        try {
            String expectedSQL = "select SUM(duration) from track";

            when(dataSource.getConnection()).thenReturn(connection);
            when(connection.prepareStatement(expectedSQL)).thenReturn(preparedStatement);
            when(preparedStatement.executeQuery()).thenReturn(resultSet);
            when(resultSet.next()).thenReturn(false);

            playlistDAO.setDataSource(dataSource);
            int duration = playlistDAO.getTotalDuration("token");

            verify(dataSource).getConnection();
            verify(connection).prepareStatement(expectedSQL);
            verify(preparedStatement).executeQuery();

        } catch (Exception e){
            fail(e.getMessage());
        }
    }
}
