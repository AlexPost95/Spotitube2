package com.oose.dea.domain;

public class Playlist {

    public int id;
    public String name;
    public String owner;
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
