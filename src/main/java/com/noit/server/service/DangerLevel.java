package com.noit.server.service;

import java.math.BigDecimal;
import java.util.Objects;

public class DangerLevel {

    private BigDecimal latitude;
    private BigDecimal longitude;
    private Integer level;
    private String comment;

    public BigDecimal getLatitude() {
        return latitude;
    }

    public void setLatitude(BigDecimal latitude) {
        this.latitude = latitude;
    }

    public BigDecimal getLongitude() {
        return longitude;
    }

    public void setLongitude(BigDecimal longitude) {
        this.longitude = longitude;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DangerLevel level1 = (DangerLevel) o;
        return Objects.equals(latitude, level1.latitude) &&
                Objects.equals(longitude, level1.longitude) &&
                Objects.equals(level, level1.level) &&
                Objects.equals(comment, level1.comment);
    }

    @Override
    public int hashCode() {
        return Objects.hash(latitude, longitude, level, comment);
    }
}
