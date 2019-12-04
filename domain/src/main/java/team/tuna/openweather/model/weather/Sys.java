
package team.tuna.openweather.model.weather;

public class Sys {

    public Integer type;
    public Integer id;
    public Float message;
    public String country;
    public Integer sunrise;
    public Integer sunset;

    @Override
    public String toString() {
        return "Sys{" +
                "country='" + country + '\'' +
                ", id=" + id +
                ", message=" + message +
                ", sunrise=" + sunrise +
                ", sunset=" + sunset +
                ", type=" + type +
                '}';
    }
}
