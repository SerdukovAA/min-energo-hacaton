package com.noit.server.service;

public class Analytica {

    public Analytica(String region, String city, Integer windForce, Double humidity, Double cloudy, Double pressure) {
        this.region = region;
        this.city = city;
        this.windForce = windForce;
        this.humidity = humidity;
        this.cloudy = cloudy;
        this.pressure = pressure;
    }

    private String region;
    private String city;
    private Integer windForce;
    private Double humidity;
    private Double cloudy;
    private Double pressure;

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Integer getWindForce() {
        return windForce;
    }

    public void setWindForce(Integer windForce) {
        this.windForce = windForce;
    }

    public Double getHumidity() {
        return humidity;
    }

    public void setHumidity(Double humidity) {
        this.humidity = humidity;
    }

    public Double getCloudy() {
        return cloudy;
    }

    public void setCloudy(Double cloudy) {
        this.cloudy = cloudy;
    }

    public Double getPressure() {
        return pressure;
    }

    public void setPressure(Double pressure) {
        this.pressure = pressure;
    }

}
