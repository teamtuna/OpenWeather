
package com.karrel.openweather.model.weather;

public class Main {

    public Double temp;
    public Float pressure;
    public Double humidity;
    public Double temp_min;
    public Double temp_max;

    @Override
    public String toString() {
        return "Main{" +
                "humidity=" + humidity +
                ", pressure=" + pressure +
                ", temp=" + temp +
                ", tempMax=" + temp_max +
                ", tempMin=" + temp_min +
                '}';
    }
}
