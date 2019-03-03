package com.example.musicapp;

public class SongInfo {

    private long id;
    private String title;
    private String artist;
    private String album;

    public SongInfo(long songId, String title, String artist, String album){
        this.id = songId;
        this.title = title;
        this.artist = artist;
        this.album = album;
    }

    public long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getArtist() {
        return artist;
    }

    public String getAlbum() {
        return album;
    }
}
