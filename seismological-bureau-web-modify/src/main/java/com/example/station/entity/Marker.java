package com.example.station.entity;

public class Marker {
    private Double camera_latitude;
    private Double camera_longitude;
    private Double power_latitude;
    private Double power_longitude;

    @Override
    public String toString() {
        return "Marker{" +
                ", camera_latitude=" + camera_latitude +
                ", camera_longitude=" + camera_longitude +
                ", power_latitude=" + power_latitude +
                ", power_longitude=" + power_longitude +
                '}';
    }

    public Marker() {
    }

    public Marker(Double camera_latitude, Double camera_longitude, Double power_latitude, Double power_longitude) {
        this.camera_latitude = camera_latitude;
        this.camera_longitude = camera_longitude;
        this.power_latitude = power_latitude;
        this.power_longitude = power_longitude;
    }



    public Double getCamera_latitude() {
        return camera_latitude;
    }

    public void setCamera_latitude(Double camera_latitude) {
        this.camera_latitude = camera_latitude;
    }

    public Double getCamera_longitude() {
        return camera_longitude;
    }

    public void setCamera_longitude(Double camera_longitude) {
        this.camera_longitude = camera_longitude;
    }

    public Double getPower_latitude() {
        return power_latitude;
    }

    public void setPower_latitude(Double power_latitude) {
        this.power_latitude = power_latitude;
    }

    public Double getPower_longitude() {
        return power_longitude;
    }

    public void setPower_longitude(Double power_longitude) {
        this.power_longitude = power_longitude;
    }
}
