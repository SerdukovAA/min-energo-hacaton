package com.noit.server.service;

import java.util.Objects;

public class Analytica {

    private String city;
    private String time;
    private String temperature;
    private String windspeed;
    private String precipitation;
    private String cloudiness;
    private String pressure;

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    public String getWindspeed() {
        return windspeed;
    }

    public void setWindspeed(String windspeed) {
        this.windspeed = windspeed;
    }

    public String getPrecipitation() {
        return precipitation;
    }

    public void setPrecipitation(String precipitation) {
        this.precipitation = precipitation;
    }

    public String getCloudiness() {
        return cloudiness;
    }

    public void setCloudiness(String cloudiness) {
        this.cloudiness = cloudiness;
    }

    public String getPressure() {
        return pressure;
    }

    public void setPressure(String pressure) {
        this.pressure = pressure;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Analytica analytica = (Analytica) o;
        return Objects.equals(city, analytica.city) &&
                Objects.equals(time, analytica.time) &&
                Objects.equals(temperature, analytica.temperature) &&
                Objects.equals(windspeed, analytica.windspeed) &&
                Objects.equals(precipitation, analytica.precipitation) &&
                Objects.equals(cloudiness, analytica.cloudiness) &&
                Objects.equals(pressure, analytica.pressure);
    }

    @Override
    public int hashCode() {
        return Objects.hash(city, time, temperature, windspeed, precipitation, cloudiness, pressure);
    }
}
