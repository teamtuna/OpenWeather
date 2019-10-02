
package com.karrel.openweather.model.weather;

public class Weather {

    public Integer id;
    public String main;
    public String description;
    public String icon;

    @Override
    public String toString() {
        return "Weather{" +
                "description='" + description + '\'' +
                ", icon='" + icon + '\'' +
                ", id=" + id +
                ", main='" + main + '\'' +
                '}';
    }
}
