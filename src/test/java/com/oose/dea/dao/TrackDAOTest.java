package com.oose.dea.dao;

import com.oose.dea.dao.TrackDAO;
import com.oose.dea.domain.Track;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityNotFoundException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.verify;

public class TrackDAOTest {

    TrackDAO trackDAO;
    DataSource dataSource;
    Connection connection;
    PreparedStatement preparedStatement;
    ResultSet resultSet;

    /**
     * Generating mocks that every test can use
     */
    @BeforeEach
    public void setup(){
        trackDAO = new TrackDAO();
        dataSource = mock(DataSource.class);
        connection = mock(Connection.class);
        preparedStatement = mock(PreparedStatement.class);
        resultSet = mock(ResultSet.class);
    }

    /**
     * Test for retrieving track with specific id
     */
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

    /**
     * Test for retrieving all tracks
     */
    @Test
    public void getTracksTest(){
        try {
            String expectedSQL = "select * from track where id not in (select t.id from track t inner join playlisttracks pt on t.id = pt.trackId where pt.playlistId = ?)";

            when(dataSource.getConnection()).thenReturn(connection);
            when(connection.prepareStatement(expectedSQL)).thenReturn(preparedStatement);
            when(preparedStatement.executeQuery()).thenReturn(resultSet);
            when(resultSet.next()).thenReturn(false);

            trackDAO.setDataSource(dataSource);
            int playlistId = 1;
            ArrayList<Track> tracks = trackDAO.getTracks(playlistId);

            verify(dataSource).getConnection();
            verify(connection).prepareStatement(expectedSQL);
            verify(preparedStatement).setInt(1, playlistId);
            verify(preparedStatement).executeQuery();

        } catch (Exception e){
            fail(e.getMessage());
        }
    }
}
