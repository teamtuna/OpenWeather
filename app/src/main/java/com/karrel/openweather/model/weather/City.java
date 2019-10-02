
package com.karrel.openweather.model.weather;

public class City {

    public Integer id;
    public String name;
    public Coord coord;
    public String country;


    @Override
    public String toString() {
        return "City{" +
                "coord=" + coord +
                ", country='" + country + '\'' +
                ", id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
