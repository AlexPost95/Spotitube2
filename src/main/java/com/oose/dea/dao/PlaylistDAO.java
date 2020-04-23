package com.oose.dea.dao;

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
public class PlaylistDAO implements IPlaylistDAO{

    @Resource(name = "jdbc/spotitube")
    DataSource dataSource;
    String tokenMissing = "Token is missing";
    String dataError = "Error in retrieving data from the database";
    String valueNotCorrect = " is not correct";

    @Override
    public Playlist getPlaylistById(int playlistId, String owner) throws SpotitubeUnauthorizedErrorException, SpotitubeServerErrorException {

        if (owner == null) {
            throw new SpotitubeUnauthorizedErrorException(tokenMissing);
        }

        if (playlistId <= 0){
            throw new SpotitubeServerErrorException("PlaylistId" + valueNotCorrect);
        }

        try (Connection connection = dataSource.getConnection()) {
            String sql = "select * from playlist where id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, playlistId);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet == null){
                throw new SpotitubeServerErrorException(dataError);
            }

            while(resultSet.next()){

                Playlist playlist = new Playlist();
                playlist.setId(resultSet.getInt("id"));
                playlist.setName(resultSet.getString("name"));
                playlist.setOwner(resultSet.getString("owner"));

                return playlist;
            }

        } catch (SQLException e){
            e.printStackTrace();
            return null;
        }
        return null;
    }

    @Override
    public ArrayList<Playlist> getPlaylists(String owner) throws SpotitubeUnauthorizedErrorException, SpotitubeServerErrorException {

        if (owner == null) {
            throw new SpotitubeUnauthorizedErrorException(tokenMissing);
        }

        ArrayList<Playlist> playlists = new ArrayList<Playlist>();

        try (Connection connection = dataSource.getConnection()) {
            String sql = "select * from playlist";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet == null){
                throw new SpotitubeServerErrorException(dataError);
            }

            while(resultSet.next()){

                Playlist playlist = new Playlist();
                playlist.setId(resultSet.getInt("id"));
                playlist.setName(resultSet.getString("name"));
                playlist.setOwner(resultSet.getString("owner"));

                playlists.add(playlist);

            }

        } catch (SQLException e){
            e.printStackTrace();
            return null;
        }
        return playlists;
    }

    @Override
    public void deletePlaylistById(int playlistId, String owner) throws SpotitubeUnauthorizedErrorException, SpotitubeServerErrorException {

        if (owner == null) {
            throw new SpotitubeUnauthorizedErrorException(tokenMissing);
        }

        if (playlistId <= 0){
            throw new SpotitubeServerErrorException("PlaylistId" + valueNotCorrect);
        }

        try (Connection connection = dataSource.getConnection()) {
            String sql = "delete from playlist where id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, playlistId);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updatePlaylistById(int playlistId, String name, String owner) throws SpotitubeUnauthorizedErrorException, SpotitubeServerErrorException {

        if (owner == null) {
            throw new SpotitubeUnauthorizedErrorException(tokenMissing);
        }

        if (name == null){
            throw new SpotitubeServerErrorException("Name"+ valueNotCorrect);
        }

        if (playlistId <= 0){
            throw new SpotitubeServerErrorException("PlaylistId" + valueNotCorrect);
        }

        try (Connection connection = dataSource.getConnection()) {
            String sql = "update playlist set name = ? where id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, name);
            preparedStatement.setInt(2, playlistId);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public ArrayList<Track> getTracksByPlaylistId(int playlistId, String owner) throws SpotitubeUnauthorizedErrorException, SpotitubeServerErrorException {

        if (owner == null) {
            throw new SpotitubeUnauthorizedErrorException(tokenMissing);
        }

        if (playlistId <= 0){
            throw new SpotitubeServerErrorException("PlaylistId" + valueNotCorrect);
        }

        ArrayList<Track> tracks = new ArrayList<Track>();

        try (Connection connection = dataSource.getConnection()) {
            String sql = "select t.* from playlisttracks pt inner join track t on pt.trackId = t.id where pt.playlistId = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, playlistId);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet == null){
                throw new SpotitubeServerErrorException(dataError);
            }

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

                tracks.add(track);
            }

        } catch (SQLException e){
            e.printStackTrace();
            return null;
        }
        return tracks;
    }

    @Override
    public void deleteTrackFromPlaylist(int playlistId, int trackId, String owner) throws SpotitubeUnauthorizedErrorException, SpotitubeServerErrorException {

        if (owner == null) {
            throw new SpotitubeUnauthorizedErrorException(tokenMissing);
        }

        if (playlistId <= 0){
            throw new SpotitubeServerErrorException("PlaylistId" + valueNotCorrect);
        }

        if (trackId <= 0){
            throw new SpotitubeServerErrorException("TrackId" + valueNotCorrect);
        }

        try (Connection connection = dataSource.getConnection()) {
            String sql = "delete from playlisttracks where playlistId = ? AND trackId = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, playlistId);
            preparedStatement.setInt(2, trackId);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public ArrayList<Track> getAllTracks(String owner) throws SpotitubeUnauthorizedErrorException, SpotitubeServerErrorException {

        if (owner == null) {
            throw new SpotitubeUnauthorizedErrorException(tokenMissing);
        }

        ArrayList<Track> tracks = new ArrayList<Track>();

        try (Connection connection = dataSource.getConnection()) {
            String sql = "select * from track";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet == null){
                throw new SpotitubeServerErrorException(dataError);
            }

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

                tracks.add(track);
            }
        } catch (SQLException e){
            e.printStackTrace();
            return null;
        }
        return tracks;
    }

    @Override
    public void addTrackToPlaylist(int playlistId, int trackId, String owner) throws SpotitubeUnauthorizedErrorException, SpotitubeServerErrorException {

        if (owner == null) {
            throw new SpotitubeUnauthorizedErrorException(tokenMissing);
        }

        if (playlistId <= 0){
            throw new SpotitubeServerErrorException("PlaylistId" + valueNotCorrect);
        }

        if (trackId <= 0){
            throw new SpotitubeServerErrorException("TrackId" + valueNotCorrect);
        }

        try (Connection connection = dataSource.getConnection()) {
            String sql = "insert into playlisttracks values (?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, trackId);
            preparedStatement.setInt(2, playlistId);
            preparedStatement.executeUpdate();

        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public void addPlaylist(String name, String owner) throws SpotitubeUnauthorizedErrorException {

        if (owner == null) {
            throw new SpotitubeUnauthorizedErrorException(tokenMissing);
        }

        if (name == null) {
            throw new SpotitubeUnauthorizedErrorException("Name" + valueNotCorrect);
        }

        try (Connection connection = dataSource.getConnection()) {
            String sql = "insert into playlist(name, owner) values(?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, owner);

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public int getTotalDuration(String owner) throws SpotitubeUnauthorizedErrorException, SpotitubeServerErrorException {

        if (owner == null) {
            throw new SpotitubeUnauthorizedErrorException(tokenMissing);
        }

        int totalDuration = 0;

        try (Connection connection = dataSource.getConnection()) {
            String sql = "select SUM(duration) from track";

            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet == null){
                throw new SpotitubeServerErrorException(dataError);
            }

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
