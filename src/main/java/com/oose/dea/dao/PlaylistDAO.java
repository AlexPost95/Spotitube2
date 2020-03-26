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
import java.util.Date;

import static javax.faces.component.UIInput.isEmpty;

@Default
public class PlaylistDAO implements IPlaylistDAO{

    @Resource(name = "jdbc/spotitube")
    DataSource dataSource;

    @Override
    public Playlist getPlaylistById(int playlistId, String owner) {

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
//                if (owner == resultSet.getString("owner")){
//                    playlist.setOwner(true);
//                }
//                else playlist.setOwner(false);
                playlist.setOwner(resultSet.getString("owner"));
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
    public ArrayList<Playlist> getPlaylists(String owner) {

        ArrayList<Playlist> playlists = new ArrayList<Playlist>();

        try (Connection connection = dataSource.getConnection()) {
            String sql = "select * from playlist";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();

            while(resultSet.next()){

                Playlist playlist = new Playlist();
                playlist.setId(resultSet.getInt("id"));
                playlist.setName(resultSet.getString("name"));
                playlist.setOwner(resultSet.getString("owner"));
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
    public ArrayList<Playlist> deletePlaylistById(int playlistId, String owner) {

        ArrayList<Playlist> playlists = new ArrayList<Playlist>();
        try (Connection connection = dataSource.getConnection()) {
            String sql = "delete from playlist where id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, playlistId);
            preparedStatement.executeUpdate();

            String sql2 = "select * from playlist";
            PreparedStatement preparedStatement2 = connection.prepareStatement(sql2);
            ResultSet resultSet = preparedStatement2.executeQuery();

            while(resultSet.next()){

                Playlist playlist = new Playlist();
                playlist.setId(resultSet.getInt("id"));
                playlist.setName(resultSet.getString("name"));

                playlist.setOwner(resultSet.getString("owner"));
                playlist.setTracks(resultSet.getString("tracks"));

                playlists.add(playlist);

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return playlists;
    }

    @Override
    public ArrayList<Playlist> updatePlaylistById(int playlistId, String name, String owner) {

        try (Connection connection = dataSource.getConnection()) {
            String sql = "update playlist set name = ? where id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, name);
            preparedStatement.setInt(2, playlistId);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return getPlaylists(owner);

    }

    @Override
    public ArrayList<Track> getTracksByPlaylistId(int playlistId, String owner) {
        if (playlistId <= 0){
            return null;
        }

        ArrayList<Track> tracks = new ArrayList<Track>();

        try (Connection connection = dataSource.getConnection()) {
            String sql = "select t.* from playlisttracks pt inner join track t on pt.trackId = t.id where pt.playlistId = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, playlistId);
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
                track.setPlaylistId(resultSet.getInt("playlistId"));

                tracks.add(track);
            }

        } catch (SQLException e){
            e.printStackTrace();
            return null;
        }
        return tracks;
    }

    @Override
    public ArrayList<Track> deleteSongFromPlaylist(int playlistId, int trackId, String owner) {

        try (Connection connection = dataSource.getConnection()) {
            String sql = "delete from playlisttracks where playlistId = ? AND trackId = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, playlistId);
            preparedStatement.setInt(2, trackId);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return getAllTracks(owner);
    }

    @Override
    public ArrayList<Track> getAllTracks(String owner) {
        ArrayList<Track> tracks = new ArrayList<Track>();

        try (Connection connection = dataSource.getConnection()) {
            String sql = "select * from track";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
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
                track.setPlaylistId(resultSet.getInt("playlistId"));

                tracks.add(track);
            }
        } catch (SQLException e){
            e.printStackTrace();
            return null;
        }
        return tracks;
    }

    @Override
    public ArrayList<Track> addTrackToPlaylist(int playlistId, int trackId, String owner) {
        try (Connection connection = dataSource.getConnection()) {
            String sql = "insert into playlisttracks values (?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, trackId);
            preparedStatement.setInt(2, playlistId);
            preparedStatement.executeUpdate();

        } catch (SQLException e){
            e.printStackTrace();
            return null;
        }
        return getTracksByPlaylistId(playlistId, owner);
    }

    @Override
    public ArrayList<Playlist> addPlaylist(String name, String owner) {

        try (Connection connection = dataSource.getConnection()) {
            String sql = "insert into playlist(name, owner) values(?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, owner);

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return getPlaylists(owner);
    }

    @Override
    public int getTotalDuration(String owner) {

        int totalDuration = 0;

        try (Connection connection = dataSource.getConnection()) {
            String sql = "select SUM(duration) from track";

            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();

            while(resultSet.next()){
                totalDuration = resultSet.getInt("SUM(duration)");
            }

        } catch (SQLException e){
            e.printStackTrace();
            return 0;
        }
        return totalDuration;
    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }
}
