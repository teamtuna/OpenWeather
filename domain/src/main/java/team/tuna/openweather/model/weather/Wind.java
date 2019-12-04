
package team.tuna.openweather.model.weather;

public class Wind {

    public Float speed;
    public Float deg;

    @Override
    public String toString() {
        return "Wind{" +
                "deg=" + deg +
                ", speed=" + speed +
                '}';
    }
}
