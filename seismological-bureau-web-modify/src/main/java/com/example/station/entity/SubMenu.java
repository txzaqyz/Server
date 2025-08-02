package com.example.station.entity;

public class SubMenu {
    private int id;
    private String menuName;
    private String path;

    public SubMenu() {
    }

    public SubMenu(int id, String menuName, String path) {
        this.id = id;
        this.menuName = menuName;
        this.path = path;
    }

    @Override
    public String toString() {
        return "SubMenu{" +
                "id=" + id +
                ", menuName='" + menuName + '\'' +
                ", path='" + path + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
