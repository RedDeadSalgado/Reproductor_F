package com.example.reproductorbase;

public class MusicFiles {
    private String path;
    private String tittle;
    private String artist;
    private String album;
    private String duration;
    private String playlist;

    public MusicFiles(String path, String tittle, String artist, String album, String duration,String playlist) {
        this.path = path;
        this.tittle = tittle;
        this.artist = artist;
        this.album = album;
        this.duration = duration;
        this.playlist = playlist;
    }

    public MusicFiles(){

    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getTittle() {
        return tittle;
    }

    public void setTittle(String tittle) {
        this.tittle = tittle;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getPlaylist() {
        return playlist;
    }

    public void setPlaylist(String playlist) {
        this.playlist = playlist;
    }
}
