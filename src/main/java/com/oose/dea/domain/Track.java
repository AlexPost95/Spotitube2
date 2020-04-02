package com.oose.dea.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

public class Track {


        public int id;
        public String title;
        public String performer;
        public int duration;
        public String album;
        public int playcount;
        public Date publicationDate;
        public String description;
        public boolean offlineAvailable;
        public int playlistId;

    public Track() {
    }

    public Track(int id, String title, String performer, int duration, String album, int playcount, Date publicationDate, String description, boolean offlineAvailable, int playlistId) {
        this.id = id;
        this.title = title;
        this.performer = performer;
        this.duration = duration;
        this.album = album;
        this.playcount = playcount;
        this.publicationDate = publicationDate;
        this.description = description;
        this.offlineAvailable = offlineAvailable;
        this.playlistId = playlistId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPerformer() {
        return performer;
    }

    public void setPerformer(String performer) {
        this.performer = performer;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public int getPlaycount() {
        return playcount;
    }

    public void setPlaycount(int playcount) {
        this.playcount = playcount;
    }

    public Date getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(Date publicationDate) {
        this.publicationDate = publicationDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean getOfflineAvailable() {
        return offlineAvailable;
    }

    public void setOfflineAvailable(boolean offlineAvailable) {
        this.offlineAvailable = offlineAvailable;
    }

    public int getPlaylistId() {
        return playlistId;
    }

    public void setPlaylistId(int playlistId) {
        this.playlistId = playlistId;
    }

}
