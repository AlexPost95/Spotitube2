package com.oose.dea.api;

import com.oose.dea.api.dto.TrackDTO;
import com.oose.dea.api.dto.TracksDTO;
import com.oose.dea.dao.IPlaylistDAO;
import com.oose.dea.dao.ITrackDAO;
import com.oose.dea.domain.Track;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;

@Path("tracks")
public class TrackApi {

    private ITrackDAO iTrackDAO;
    private IPlaylistDAO iPlaylistDAO;

    /**
     * Get all tracks
     * @param owner the user token that comes with every request
     * @param forPlaylist the id of the playlist that the track could be added to
     * @return a response with all tracks that could be added to a specific playlist
     */
    @GET
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
    @Path("/{id}")
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
     * Set a ITrackDAO
     * @param iTrackDAO
     */
    @Inject
    public void setTrackDAO(ITrackDAO iTrackDAO) {
        this.iTrackDAO = iTrackDAO;
    }

    /**
     * Set a IPlaylistDAO
     * @param iPlaylistDAO
     */
    @Inject
    public void setPlaylistDAO(IPlaylistDAO iPlaylistDAO) {
        this.iPlaylistDAO = iPlaylistDAO;
    }


}
