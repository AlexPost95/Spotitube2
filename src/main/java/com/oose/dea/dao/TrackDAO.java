package com.oose.dea.dao;

import com.oose.dea.api.oose.dea.api.dto.PlaylistsDTO;
import com.oose.dea.domain.Playlist;
import com.oose.dea.domain.Track;

import javax.annotation.Resource;
import javax.enterprise.inject.Default;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

@Default
public class TrackDAO implements ITrackDAO{

    @Resource(name = "jdbc/spotitube")
    DataSource dataSource;

    @Override
    public Track getTrackById(int trackId) {

        if (trackId <= 0){
            return null;
        }

        try (Connection connection = dataSource.getConnection()) {
            String sql = "select * from track where id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, trackId);
            ResultSet resultSet = preparedStatement.executeQuery();

            while(resultSet.next()){

                Track track = new Track();
                track.setId(resultSet.getInt("id"));
                track.setTitle(resultSet.getString("title"));
                track.setPerformer(resultSet.getString("performer"));
                track.setDuration(resultSet.getInt("duration"));
                track.setAlbum(resultSet.getString("album"));
                track.setPlaycount(resultSet.getInt("playcount"));
                track.setPublicationDate(resultSet.getDate("publicationDate"));
                track.setDescription(resultSet.getString("description"));
                track.setOfflineAvailable(resultSet.getBoolean("offlineAvailable"));

                return track;
            }

        } catch (SQLException e){
            e.printStackTrace();
            return null;
        }
        return null;
    }

}
