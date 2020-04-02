package com.oose.dea.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "playlist")
public class Playlist {

    @Id
    @Column(name = "id", unique = true)
    public int id;

    @Column(name = "name", unique = false)
    public String name;

    @Column(name = "owner", unique = false)
    public String owner;

    @Column(name = "tracks", unique = false)
    public String tracks;

    public Playlist(){};

    public Playlist(int id, String name, String owner, String tracks) {
        this.id = id;
        this.name = name;
        this.owner = owner;
        this.tracks = tracks;
    }

    public int getId() { return id; }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String isOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getTracks() {
        return this.tracks;
    }

    public void setTracks(String tracks) {
        this.tracks = tracks;
    }
}
