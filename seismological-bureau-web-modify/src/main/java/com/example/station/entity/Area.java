package com.example.station.entity;
//因数据库名字关系，这里area对应数据库region实体类
public class Area {
    private int id;
    private String area;
    private String address;

    public Area(int id, String area, String address) {
        this.id = id;
        this.area = area;
        this.address = address;
    }

    public Area() {
    }

    @Override
    public String toString() {
        return "Area{" +
                "id=" + id +
                ", area='" + area + '\'' +
                ", address='" + address + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
