package com.oose.dea;

import com.oose.dea.dao.PlaylistDAO;
import com.oose.dea.dao.TrackDAO;
import com.oose.dea.domain.Playlist;
import com.oose.dea.domain.Track;
import org.junit.jupiter.api.Test;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.verify;

public class TrackDAOTest {

    TrackDAO trackDAO = new TrackDAO();
    DataSource dataSource = mock(DataSource.class);
    Connection connection = mock(Connection.class);
    PreparedStatement preparedStatement = mock(PreparedStatement.class);
    ResultSet resultSet = mock(ResultSet.class);

    @Test
    public void getTrackByIdTest(){
        try {
            String expectedSQL = "select * from track where id = ?";

            when(dataSource.getConnection()).thenReturn(connection);
            when(connection.prepareStatement(expectedSQL)).thenReturn(preparedStatement);
            when(preparedStatement.executeQuery()).thenReturn(resultSet);
            when(resultSet.next()).thenReturn(false);

            trackDAO.setDataSource(dataSource);
            int trackId = 1;
            Track track = trackDAO.getTrackById(trackId);

            verify(dataSource).getConnection();
            verify(connection).prepareStatement(expectedSQL);
            verify(preparedStatement).setInt(1, trackId);
            verify(preparedStatement).executeQuery();

        } catch (Exception e){
            fail(e.getMessage());
        }
    }
}
