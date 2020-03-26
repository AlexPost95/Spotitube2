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

/*
* DONE:
* GET /playlists
* GET /playlists/:id
* POST /playlists
* DELETE /playlists/id/tracks/:id
* PUT /playlists/:id
* GET /playlists/:id/tracks
* DELETE /playlists/:id
* POST/playlists/:id/tracks
* GET /tracks with queryParam 'forPlaylist'
* */

@Path("")
public class Spotitube {

    private IPlaylistDAO iPlaylistDAO;
    private ITrackDAO iTrackDAO;
    public IUserDAO iUserDAO;

    @GET
    @Path("hello")
    @Produces(MediaType.APPLICATION_JSON)
    public String hello() {

        return "hello test";
    }

    @POST
    @Path("login")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response getUser(User user) {

        String generatedToken = UUID.randomUUID().toString();

        TokenDTO tokenDTO = new TokenDTO();

        tokenDTO.user = user.getName();
        tokenDTO.token = generatedToken;

        User addedUser = iUserDAO.addUser(user.getName(), user.getPassword(), generatedToken);

        return Response.status(200).entity(tokenDTO).build();
    }

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

    @POST
    @Path("playlists")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addPlaylist(@QueryParam("token") String owner, Playlist2 playlist) {

        ArrayList<Playlist> playlists = iPlaylistDAO.addPlaylist(playlist.name, owner);

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

    @PUT
    @Path("playlists/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updatePlaylist(@QueryParam("token") String owner, Playlist playlist) {

        ArrayList<Playlist> playlists = iPlaylistDAO.updatePlaylistById(playlist.id, playlist.name, owner);

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

    @DELETE
    @Path("/playlists/{playlistId}/tracks/{trackId}")
    public Response deleteTrackFromPlaylist(@QueryParam("token") String owner, @PathParam("playlistId") int playlistId, @PathParam("trackId") int trackId) {

        if (owner == null) {
            return Response.status(403).build();
        }

        iPlaylistDAO.deleteSongFromPlaylist(playlistId, trackId, owner);
        return Response.status(200).build();
    }

    @POST
    @Path("/playlists/{playlistId}/tracks")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addTrackToPlaylist(@QueryParam("token") String owner, @PathParam("playlistId") int playlistId, Track track) {

        ArrayList<Track> tracks = iPlaylistDAO.addTrackToPlaylist(playlistId, track.id, owner);

        if (tracks == null) {
            return Response.status(400).build();
        }

        TracksDTO tracksDTO = new TracksDTO();
        tracksDTO.tracks = tracks;

        return Response.status(201).entity(tracksDTO).build();
    }

    @Inject
    public void setPlaylistDAO(IPlaylistDAO iPlaylistDAO) {
        this.iPlaylistDAO = iPlaylistDAO;
    }

    @Inject
    public void setTrackDAO(ITrackDAO iTrackDAO) {
        this.iTrackDAO = iTrackDAO;
    }

    @Inject
    public void setUserDAO(IUserDAO iUserDAO) {
        this.iUserDAO = iUserDAO;
    }

}
