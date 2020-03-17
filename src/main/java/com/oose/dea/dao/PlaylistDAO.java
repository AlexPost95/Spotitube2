package com.oose.dea.dao;

import com.oose.dea.api.oose.dea.api.dto.PlaylistsDTO;
import com.oose.dea.domain.Playlist;

import javax.annotation.Resource;
import javax.enterprise.inject.Default;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

@Default
public class PlaylistDAO implements IPlaylistDAO{

    @Resource(name = "jdbc/spotitube")
    DataSource dataSource;


    @Override
    public Playlist getPlaylistById(int playlistId) {

        if (playlistId <= 0){
            return null;
        }

        try (Connection connection = dataSource.getConnection()) {
            String sql = "select * from playlist where id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, playlistId);
            ResultSet resultSet = preparedStatement.executeQuery();

            while(resultSet.next()){

                Playlist playlist = new Playlist();
                playlist.setId(resultSet.getInt("id"));
                playlist.setName(resultSet.getString("name"));
                playlist.setOwner(resultSet.getBoolean("owner"));
                playlist.setTracks(resultSet.getString("tracks"));


                return playlist;
            }

        } catch (SQLException e){
            e.printStackTrace();
            return null;
        }
        return null;
    }

    @Override
    public ArrayList<Playlist> getPlaylists() {

        ArrayList<Playlist> playlists = new ArrayList<Playlist>();

        try (Connection connection = dataSource.getConnection()) {
            String sql = "select * from playlist";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();

            while(resultSet.next()){

                Playlist playlist = new Playlist();
                playlist.setId(resultSet.getInt("id"));
                playlist.setName(resultSet.getString("name"));
                playlist.setOwner(resultSet.getBoolean("owner"));
                playlist.setTracks(resultSet.getString("tracks"));

                playlists.add(playlist);

            }


        } catch (SQLException e){
            e.printStackTrace();
            return null;
        }
        return playlists;
    }

    @Override
    public void deletePlaylistById(int playlistId) {

        try (Connection connection = dataSource.getConnection()) {
            String sql = "delete from playlist where id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, playlistId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
