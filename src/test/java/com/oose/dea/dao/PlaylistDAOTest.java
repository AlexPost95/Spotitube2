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

    /**
     * Creating mocks that every test can use
     */
        @BeforeEach
        public void setup(){
            playlistDAO = new PlaylistDAO();
            dataSource = mock(DataSource.class);
            connection = mock(Connection.class);
            preparedStatement = mock(PreparedStatement.class);
            resultSet = mock(ResultSet.class);
        }

    /**
     * Test retrieving a playlist with specific id
     */
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

    /**
     * Test retrieving all playlists
     */
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

    /**
     * Test for deleting a playlist
     */
    @Test
    public void deletePlaylistTest(){
        try {
            String expectedSQL = "delete from playlist where id = ?";

            when(dataSource.getConnection()).thenReturn(connection);
            when(connection.prepareStatement(expectedSQL)).thenReturn(preparedStatement);
            when(preparedStatement.executeUpdate()).thenReturn(1);
            when(resultSet.next()).thenReturn(false);

            playlistDAO.setDataSource(dataSource);
            int playlistId = 1;
            playlistDAO.deletePlaylistById(playlistId, "token");

            verify(dataSource).getConnection();
            verify(connection).prepareStatement(expectedSQL);
            verify(preparedStatement).setInt(1, playlistId);
            verify(preparedStatement).executeUpdate();

        } catch (Exception e){
            fail(e.getMessage());
        }
    }


    /**
     * Test for updating a playlist
     */
    @Test
    public void updatePlaylistByIdTest(){
        try {
            String expectedSQL = "update playlist set name = ? where id = ?";

            when(dataSource.getConnection()).thenReturn(connection);
            when(connection.prepareStatement(expectedSQL)).thenReturn(preparedStatement);
            when(preparedStatement.executeUpdate()).thenReturn(1);
            when(resultSet.next()).thenReturn(false);

            playlistDAO.setDataSource(dataSource);
            int playlistId = 1;
            String name = "new playlist name";
            playlistDAO.updatePlaylistById(playlistId, name, "token");

            verify(dataSource).getConnection();
            verify(connection).prepareStatement(expectedSQL);
            verify(preparedStatement).setString(1, name);
            verify(preparedStatement).setInt(2, playlistId);
            verify(preparedStatement).executeUpdate();

        } catch (Exception e){
            fail(e.getMessage());
        }
    }

    /**
     * Test for retrieving all tracks that belong to a specific playlist
     */
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

    /**
     * Test for deleting a song from a playlist
     */
    @Test
    public void deleteTrackFromPlaylistTest(){
        try {
            String expectedSQL = "delete from playlisttracks where playlistId = ? AND trackId = ?";

            when(dataSource.getConnection()).thenReturn(connection);
            when(connection.prepareStatement(expectedSQL)).thenReturn(preparedStatement);
            when(preparedStatement.executeUpdate()).thenReturn(1);
            when(resultSet.next()).thenReturn(false);

            playlistDAO.setDataSource(dataSource);
            int playlistId = 1;
            int trackId = 1;
            playlistDAO.deleteTrackFromPlaylist(playlistId, trackId, "token");

            verify(dataSource).getConnection();
            verify(connection).prepareStatement(expectedSQL);
            verify(preparedStatement).setInt(1, playlistId);
            verify(preparedStatement).setInt(2, trackId);
            verify(preparedStatement).executeUpdate();

        } catch (Exception e){
            fail(e.getMessage());
        }
    }

    /**
     * Test for retrieving all tracks
     */
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

    /**
     * Test for adding a track to a playlist
     */
    @Test
    public void addTrackToPlaylistTest(){
        try {
            String expectedSQL = "insert into playlisttracks values (?, ?)";

            when(dataSource.getConnection()).thenReturn(connection);
            when(connection.prepareStatement(expectedSQL)).thenReturn(preparedStatement);
            when(preparedStatement.executeUpdate()).thenReturn(1);
            when(resultSet.next()).thenReturn(false);

            playlistDAO.setDataSource(dataSource);
            int playlistId = 1;
            int trackId = 1;
            playlistDAO.addTrackToPlaylist(trackId, playlistId, "token");

            verify(dataSource).getConnection();
            verify(connection).prepareStatement(expectedSQL);
            verify(preparedStatement).setInt(1, trackId);
            verify(preparedStatement).setInt(2, playlistId);
            verify(preparedStatement).executeUpdate();

        } catch (Exception e){
            fail(e.getMessage());
        }

    }

    /**
     * Test for adding a playlist
     */
    @Test
    public void addPlaylistTest(){
        try {

            String expectedSQL = "insert into playlist(name, owner) values(?, ?)";

            when(dataSource.getConnection()).thenReturn(connection);
            when(connection.prepareStatement(expectedSQL)).thenReturn(preparedStatement);
            when(preparedStatement.executeUpdate()).thenReturn(1);
            when(resultSet.next()).thenReturn(false);

            playlistDAO.setDataSource(dataSource);

            playlistDAO.addPlaylist("name", "token");

            verify(dataSource).getConnection();
            verify(connection).prepareStatement(expectedSQL);
            verify(preparedStatement).setString(1, "name");
            verify(preparedStatement).setString(2, "token");

            verify(preparedStatement).executeUpdate();

        } catch (Exception e){
            fail(e.getMessage());
        }
    }

    /**
     * Test for retrieving the lengt of all songs that are in playlist
     */
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
