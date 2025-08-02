package com.example.station.entity;

public class Station {
    private int id;
    //这里因为基础设置问题，故而不用驼峰命名
    private int region_id;
    private int area_id;
    private String address;
    private int level;
    private double coordinate_x;
    private double coordinate_y;
    private Boolean is_center;

    public Station() {
    }

    public Station(int id, int region_id, int area_id, String address, int level, double coordinate_x, double coordinate_y, Boolean is_center) {
        this.id = id;
        this.region_id = region_id;
        this.area_id = area_id;
        this.address = address;
        this.level = level;
        this.coordinate_x = coordinate_x;
        this.coordinate_y = coordinate_y;
        this.is_center = is_center;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRegion_id() {
        return region_id;
    }

    public void setRegion_id(int region_id) {
        this.region_id = region_id;
    }

    public int getArea_id() {
        return area_id;
    }

    public void setArea_id(int area_id) {
        this.area_id = area_id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public double getCoordinate_x() {
        return coordinate_x;
    }

    public void setCoordinate_x(double coordinate_x) {
        this.coordinate_x = coordinate_x;
    }

    public double getCoordinate_y() {
        return coordinate_y;
    }

    public void setCoordinate_y(double coordinate_y) {
        this.coordinate_y = coordinate_y;
    }

    public Boolean getIs_center() {
        return is_center;
    }

    public void setIs_center(Boolean is_center) {
        this.is_center = is_center;
    }

    @Override
    public String toString() {
        return "Station{" +
                "id=" + id +
                ", region_id=" + region_id +
                ", area_id=" + area_id +
                ", address='" + address + '\'' +
                ", level=" + level +
                ", coordinate_x=" + coordinate_x +
                ", coordinate_y=" + coordinate_y +
                ", is_center=" + is_center +
                '}';
    }
}
