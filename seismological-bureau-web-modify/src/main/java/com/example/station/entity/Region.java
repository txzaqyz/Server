package com.example.station.entity;
//因数据库名字关系这里region对应数据库station的实体类
public class Region {
    private int id;
    private String area;
    private String address;
    private int level;
    private int regionId;

    public Region(int id, String area, String address, int level, int regionId) {
        this.id = id;
        this.area = area;
        this.address = address;
        this.level = level;
        this.regionId = regionId;
    }

    public Region() {
    }

    @Override
    public String toString() {
        return "Region{" +
                "id=" + id +
                ", area='" + area + '\'' +
                ", address='" + address + '\'' +
                ", level=" + level +
                ", regionId=" + regionId +
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

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getRegionId() {
        return regionId;
    }

    public void setRegionId(int regionId) {
        this.regionId = regionId;
    }
}
