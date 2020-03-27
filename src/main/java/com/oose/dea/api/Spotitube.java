package com.oose.dea.api;

import com.oose.dea.api.oose.dea.api.dto.*;
import com.oose.dea.dao.IPlaylistDAO;
import com.oose.dea.dao.ITrackDAO;
import com.oose.dea.dao.IUserDAO;
import com.oose.dea.domain.*;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.UUID;

/**
 * REST Api
 */
@Path("")
public class Spotitube {

    private IPlaylistDAO iPlaylistDAO;
    private ITrackDAO iTrackDAO;
    public IUserDAO iUserDAO;

    /**
     * Print a string when accessing /hello. Just to test if the program is running
     * @return
     */
    @GET
    @Path("hello")
    @Produces(MediaType.APPLICATION_JSON)
    public String hello() {

        return "hello test";
    }

    /**
     * Log a user in
     * @param user the user that is sent within the body of the request
     * @return return a response code and a user token
     */
    @POST
    @Path("login")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response getUser(User user) {

        String generatedToken = UUID.randomUUID().toString();

        TokenDTO tokenDTO = new TokenDTO();

        tokenDTO.user = user.getName();
        tokenDTO.token = generatedToken;

        iUserDAO.addUser(user.getName(), user.getPassword(), generatedToken);

        return Response.status(200).entity(tokenDTO).build();
    }

    /**
     * Get all playlists
     * @param owner the user token that comes with every request
     * @return a response with the list of all playlists
     */
    @GET
    @Path("playlists")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPlaylists(@QueryParam("token") String owner){

        ArrayList<Playlist> playlists = iPlaylistDAO.getPlaylists(owner);
        int totalDuration = iPlaylistDAO.getTotalDuration(owner);

        if (owner == null) {
            return Response.status(403).build();
        }
        if (playlists == null) {
            return Response.status(400).build();
        }

        PlaylistsDTO playlistsDTO = new PlaylistsDTO();
        playlistsDTO.playlists = playlists;
        playlistsDTO.length = totalDuration;

        return Response.status(200).entity(playlistsDTO).build();
    }

    /**
     * Get a specific playlist by id
     * @param owner the user token that comes with every request
     * @param id the id of the playlist that is being retrieved
     * @return a response with a single playlist in the body
     */
    @GET
    @Path("playlists/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPlaylist(@QueryParam("token") String owner, @PathParam("id") int id){

        Playlist playlist = iPlaylistDAO.getPlaylistById(id, owner);

        if (owner == null) {
            return Response.status(403).build();
        }
        if (playlist == null) {
            return Response.status(400).build();
        }

        PlaylistDTO playlistDTO = new PlaylistDTO();
        playlistDTO.id = playlist.getId();
        playlistDTO.name = playlist.getName();
        playlistDTO.owner = playlist.isOwner();
        playlistDTO.tracks = playlist.getTracks();

        return Response.status(200).entity(playlistDTO).build();
    }

    /**
     * Delete a specific playlist
     * @param owner the user token that comes with every request
     * @param id the id op the playlist that needs to be deleted
     * @return a list of all playlists
     */
    @DELETE
    @Path("playlists/{id}")
    public Response deletePlaylist(@QueryParam("token") String owner, @PathParam("id") int id) {
        iPlaylistDAO.deletePlaylistById(id, owner);

        ArrayList<Playlist> playlists = iPlaylistDAO.getPlaylists(owner);
        int totalDuration = iPlaylistDAO.getTotalDuration(owner);

        if (owner == null) {
            return Response.status(403).build();
        }
        if (playlists == null) {
            return Response.status(400).build();
        }

        PlaylistsDTO playlistsDTO = new PlaylistsDTO();
        playlistsDTO.playlists = playlists;
        playlistsDTO.length = totalDuration;

        return Response.status(200).entity(playlistsDTO).build();
    }

    /**
     * Add a new playlist
     * @param owner the user token that comes with every request
     * @param playlist the playlist that is attached to the response body
     * @return a response with a list of all playlists
     */
    @POST
    @Path("playlists")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addPlaylist(@QueryParam("token") String owner, Playlist2 playlist) {

        iPlaylistDAO.addPlaylist(playlist.name, owner);
        ArrayList<Playlist> playlists = iPlaylistDAO.getPlaylists(owner);

        if (owner == null) {
            return Response.status(403).build();
        }
        if (playlists == null) {
            return Response.status(400).build();
        }

        PlaylistsDTO playlistsDTO = new PlaylistsDTO();
        playlistsDTO.playlists = playlists;
        playlistsDTO.length = iPlaylistDAO.getTotalDuration(owner);

        return Response.status(201).entity(playlistsDTO).build();
    }

    /**
     * Update a playlist
     * @param owner the user token that comes with every request
     * @param playlist the playlist that needs to be updated
     * @return a response with the list of updated playlists
     */
    @PUT
    @Path("playlists/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updatePlaylist(@QueryParam("token") String owner, Playlist playlist) {

        iPlaylistDAO.updatePlaylistById(playlist.id, playlist.name, owner);
        ArrayList<Playlist> playlists = iPlaylistDAO.getPlaylists(owner);

        if (owner == null) {
            return Response.status(403).build();
        }
        if (playlists == null) {
            return Response.status(400).build();
        }

        PlaylistsDTO playlistsDTO = new PlaylistsDTO();
        playlistsDTO.playlists = playlists;
        playlistsDTO.length = iPlaylistDAO.getTotalDuration(owner);

        return Response.status(200).entity(playlistsDTO).build();
    }

    /**
     * Get all tracks
     * @param owner the user token that comes with every request
     * @param forPlaylist the id of the playlist that the track could be added to
     * @return a response with all tracks that could be added to a specific playlist
     */
    @GET
    @Path("tracks")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getTracks(@QueryParam("token") String owner, @QueryParam("forPlaylist") int forPlaylist){

        ArrayList<Track> tracks = iTrackDAO.getTracks(forPlaylist);

        if (owner == null) {
            return Response.status(403).build();
        }
        if (tracks == null) {
            return Response.status(400).build();
        }

        TracksDTO tracksDTO = new TracksDTO();
        tracksDTO.tracks = tracks;

        return Response.status(200).entity(tracksDTO).build();
    }

    /**
     * Get a track with specific id
     * @param owner the user token that comes with every request
     * @param id the id of the track
     * @return a response with a single track
     */
    @GET
    @Path("tracks/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getTrack(@QueryParam("token") String owner, @PathParam("id") int id){

        Track track = iTrackDAO.getTrackById(id);

        if (owner == null) {
            return Response.status(403).build();
        }
        if (track == null) {
            return Response.status(400).build();
        }

        TrackDTO trackDTO = new TrackDTO();
        trackDTO.id = track.getId();
        trackDTO.title = track.getTitle();
        trackDTO.performer = track.getPerformer();
        trackDTO.duration = track.getDuration();
        trackDTO.album = track.getAlbum();
        trackDTO.playount = track.getPlaycount();
        trackDTO.publicationDate = track.getPublicationDate();
        trackDTO.description = track.getDescription();
        trackDTO.offlineAvailable = track.getOfflineAvailable();
        trackDTO.playlistId = track.getPlaylistId();

        return Response.status(200).entity(trackDTO).build();
    }

    /**
     * Get all tracks for a specific playlist
     * @param owner the user token that comes with every request
     * @param id the id of the playlist
     * @return a response with a list of tracks that belong to the playlist with given id
     */
    @GET
    @Path("playlists/{id}/tracks")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getTracksByPlaylist(@QueryParam("token") String owner, @PathParam("id") int id){

        ArrayList<Track> tracks = iPlaylistDAO.getTracksByPlaylistId(id, owner);

        if (owner == null) {
            return Response.status(403).build();
        }
        if (tracks == null) {
            return Response.status(400).build();
        }

        TracksDTO tracksDTO = new TracksDTO();
        tracksDTO.tracks = tracks;

        return Response.status(200).entity(tracksDTO).build();
    }

    /**
     * Delete a track from a playlist
     * @param owner the user token that comes with every request
     * @param playlistId the playlist where the track needs to be deleted from
     * @param trackId the track that needs to be deleted
     * @return a response that indicated success
     */
    @DELETE
    @Path("/playlists/{playlistId}/tracks/{trackId}")
    public Response deleteTrackFromPlaylist(@QueryParam("token") String owner, @PathParam("playlistId") int playlistId, @PathParam("trackId") int trackId) {

        if (owner == null) {
            return Response.status(403).build();
        }

        iPlaylistDAO.deleteTrackFromPlaylist(playlistId, trackId, owner);
        ArrayList<Track> tracks = iPlaylistDAO.getAllTracks(owner);

        TracksDTO tracksDTO = new TracksDTO();
        tracksDTO.tracks = tracks;

        return Response.status(200).entity(tracksDTO).build();
    }

    /**
     * Add a track to a playlist
     * @param owner the user token that comes with every request
     * @param playlistId the id of the playlist that the track needs to be added to
     * @param track the id of the track that needs to be added to the playlist
     * @return a response with all tracks
     */
    @POST
    @Path("/playlists/{playlistId}/tracks")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addTrackToPlaylist(@QueryParam("token") String owner, @PathParam("playlistId") int playlistId, Track track) {

        iPlaylistDAO.addTrackToPlaylist(playlistId, track.id, owner);
        ArrayList<Track> tracks = iPlaylistDAO.getTracksByPlaylistId(playlistId, owner);

        if (tracks == null) {
            return Response.status(400).build();
        }

        TracksDTO tracksDTO = new TracksDTO();
        tracksDTO.tracks = tracks;

        return Response.status(201).entity(tracksDTO).build();
    }

    /**
     * Set a IPlaylistDAO
     * @param iPlaylistDAO
     */
    @Inject
    public void setPlaylistDAO(IPlaylistDAO iPlaylistDAO) {
        this.iPlaylistDAO = iPlaylistDAO;
    }

    /**
     * Set a ITrackDAO
     * @param iTrackDAO
     */
    @Inject
    public void setTrackDAO(ITrackDAO iTrackDAO) {
        this.iTrackDAO = iTrackDAO;
    }

    /**
     * Set a IUserDAO
     * @param iUserDAO
     */
    @Inject
    public void setUserDAO(IUserDAO iUserDAO) {
        this.iUserDAO = iUserDAO;
    }

}
