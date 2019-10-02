
package com.karrel.openweather.model.weather;

public class CurrentWeather {

    public Integer id;
    public String name;
    public Coord coord;
    public Main main;
    public Integer dt;
    public Wind wind;
    public Sys sys;
    public Object rain;
    public Object snow;
    public Clouds clouds;
    public java.util.List<Weather> weather = null;
    public String dt_txt;


    @Override
    public String toString() {
        return "CurrentWeather{" +
                "clouds=" + clouds +
                ", coord=" + coord +
                ", dt=" + dt +
                ", id=" + id +
                ", main=" + main +
                ", name='" + name + '\'' +
                ", rain=" + rain +
                ", snow=" + snow +
                ", sys=" + sys +
                ", weather=" + weather +
                ", wind=" + wind +
                ", dt_txt=" + dt_txt +
                '}';
    }
}
