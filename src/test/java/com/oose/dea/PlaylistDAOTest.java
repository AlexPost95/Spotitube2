package com.oose.dea;

import com.oose.dea.dao.PlaylistDAO;
import com.oose.dea.domain.Playlist;
import org.junit.jupiter.api.Test;

import javax.sql.DataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.*;

public class PlaylistDAOTest {

    @Test
    public void getPlaylistByIdTest(){
        try {
            String expectedSQL = "select * from playlist where id = ?";
            PlaylistDAO playlistDAO = new PlaylistDAO();
            DataSource dataSource = mock(DataSource.class);
            Connection connection = mock(Connection.class);
            PreparedStatement preparedStatement = mock(PreparedStatement.class);
            ResultSet resultSet = mock(ResultSet.class);

            when(dataSource.getConnection()).thenReturn(connection);
            when(connection.prepareStatement(expectedSQL)).thenReturn(preparedStatement);
            when(preparedStatement.executeQuery()).thenReturn(resultSet);
            when(resultSet.next()).thenReturn(false);


            playlistDAO.setDataSource(dataSource);
            int playlistId = 1;
            Playlist playlist = playlistDAO.getPlaylistById(playlistId);

            verify(dataSource).getConnection();
            verify(connection).prepareStatement(expectedSQL);
            verify(preparedStatement).setInt(1, playlistId);
            verify(preparedStatement).executeQuery();

        } catch (Exception e){
            fail(e.getMessage());
        }
    }
}
