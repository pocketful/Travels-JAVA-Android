package com.byethost17.pocketful.travels;

public class Kelione {
    private String name;
    private String continent;
    private int duration;
    private String seasons;
    private String traveltype;

    public Kelione(String name, String continent, int duration, String seasons, String traveltype) {
        this.name = name;
        this.continent = continent;
        this.duration = duration;
        this.seasons = seasons;
        this.traveltype = traveltype;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContinent() {
        return continent;
    }

    public void setContinent(String continent) {
        this.continent = continent;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getSeasons() { return seasons; }

    public void setSeasons(String seasons) {
        this.seasons = seasons;
    }

    public String getTraveltype() {
        return traveltype;
    }

    public void setTraveltype(String traveltype) {
        this.traveltype = traveltype;
    }
}
