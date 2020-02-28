package com.androidtutorialshub.DungeonsandNaggins.model;

public class Pub {
    private int id;
    private String name;
    private String location;
    private String time;
    private String games;
    private String lat;
    private String lng;

    public Pub(int id, String name, String location, String lat, String lng){
        this.id = id;
        this.name = name;
        this.location = location;
        this.lat = lat;
        this.lng = lng;

    }

    public Pub()
    {}

    public String getLat() {
        return lat;
    }

    public void setLat(String l) {
        lat = l;
    }

    public String getLng() {
        return lng;
    }

    public void setLng(String l) {
        lng = l;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getGames() {
        return games;
    }

    public void setGames(String games) {
        this.games = games;
    }
}
