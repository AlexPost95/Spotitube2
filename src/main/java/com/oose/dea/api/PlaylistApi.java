package com.oose.dea.api;

import com.oose.dea.api.dto.PlaylistDTO;
import com.oose.dea.api.dto.PlaylistsDTO;
import com.oose.dea.api.dto.TracksDTO;
import com.oose.dea.dao.IPlaylistDAO;
import com.oose.dea.dao.ITrackDAO;
import com.oose.dea.dao.SpotitubeServerErrorException;
import com.oose.dea.dao.SpotitubeUnauthorizedErrorException;
import com.oose.dea.domain.Playlist;
import com.oose.dea.domain.Playlist2;
import com.oose.dea.domain.Track;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;

@Path("playlists")
public class PlaylistApi {

    private IPlaylistDAO iPlaylistDAO;
    private ITrackDAO iTrackDAO;

    /**
     * Get all playlists
     * @param owner the user token that comes with every request
     * @throws SpotitubeServerErrorException
     * @throws SpotitubeUnauthorizedErrorException
     * @return a response with the list of all playlists
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPlaylists(@QueryParam("token") String owner)
            throws SpotitubeUnauthorizedErrorException, SpotitubeServerErrorException {

        ArrayList<Playlist> playlists = iPlaylistDAO.getPlaylists(owner);
        int totalDuration = iPlaylistDAO.getTotalDuration(owner);


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
     * @throws SpotitubeServerErrorException
     * @throws SpotitubeUnauthorizedErrorException
     * @return a response with a single playlist in the body
     */
    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPlaylist(@QueryParam("token") String owner, @PathParam("id") int id)
            throws SpotitubeUnauthorizedErrorException, SpotitubeServerErrorException {

        Playlist playlist = iPlaylistDAO.getPlaylistById(id, owner);

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
     * @throws SpotitubeServerErrorException
     * @throws SpotitubeUnauthorizedErrorException
     * @return a list of all playlists
     */
    @DELETE
    @Path("/{id}")
    public Response deletePlaylist(@QueryParam("token") String owner, @PathParam("id") int id)
            throws SpotitubeUnauthorizedErrorException, SpotitubeServerErrorException {
        iPlaylistDAO.deletePlaylistById(id, owner);

        ArrayList<Playlist> playlists = iPlaylistDAO.getPlaylists(owner);
        int totalDuration = iPlaylistDAO.getTotalDuration(owner);

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
     * @throws SpotitubeServerErrorException
     * @throws SpotitubeUnauthorizedErrorException
     * @return a response with a list of all playlists
     */
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addPlaylist(@QueryParam("token") String owner, Playlist2 playlist)
            throws SpotitubeUnauthorizedErrorException, SpotitubeServerErrorException {

        iPlaylistDAO.addPlaylist(playlist.name, owner);
        ArrayList<Playlist> playlists = iPlaylistDAO.getPlaylists(owner);

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
     * @throws SpotitubeServerErrorException
     * @throws SpotitubeUnauthorizedErrorException
     * @return a response with the list of updated playlists
     */
    @PUT
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updatePlaylist(@QueryParam("token") String owner, Playlist playlist)
            throws SpotitubeUnauthorizedErrorException, SpotitubeServerErrorException {

        iPlaylistDAO.updatePlaylistById(playlist.id, playlist.name, owner);
        ArrayList<Playlist> playlists = iPlaylistDAO.getPlaylists(owner);

        if (playlists == null) {
            return Response.status(400).build();
        }

        PlaylistsDTO playlistsDTO = new PlaylistsDTO();
        playlistsDTO.playlists = playlists;
        playlistsDTO.length = iPlaylistDAO.getTotalDuration(owner);

        return Response.status(200).entity(playlistsDTO).build();
    }

    /**
     * Get all tracks for a specific playlist
     * @param owner the user token that comes with every request
     * @param id the id of the playlist
     * @throws SpotitubeServerErrorException
     * @throws SpotitubeUnauthorizedErrorException
     * @return a response with a list of tracks that belong to the playlist with given id
     */
    @GET
    @Path("/{id}/tracks")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getTracksByPlaylist(@QueryParam("token") String owner, @PathParam("id") int id)
            throws SpotitubeUnauthorizedErrorException, SpotitubeServerErrorException {

        ArrayList<Track> tracks = iPlaylistDAO.getTracksByPlaylistId(id, owner);

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
     * @throws SpotitubeServerErrorException
     * @throws SpotitubeUnauthorizedErrorException
     * @return a response that indicated success
     */
    @DELETE
    @Path("/{playlistId}/tracks/{trackId}")
    public Response deleteTrackFromPlaylist(@QueryParam("token") String owner, @PathParam("playlistId") int playlistId,
                                            @PathParam("trackId") int trackId) throws SpotitubeUnauthorizedErrorException, SpotitubeServerErrorException {


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
     * @throws SpotitubeServerErrorException
     * @throws SpotitubeUnauthorizedErrorException
     * @return a response with all tracks
     */
    @POST
    @Path("/{playlistId}/tracks")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addTrackToPlaylist(@QueryParam("token") String owner, @PathParam("playlistId") int playlistId, Track track)
            throws SpotitubeUnauthorizedErrorException, SpotitubeServerErrorException {

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
}
