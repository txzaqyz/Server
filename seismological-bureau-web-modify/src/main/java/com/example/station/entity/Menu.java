package com.example.station.entity;

import java.util.List;

public class Menu {
    private int id;
    private String menuName;
    private String path;
    private String icon;
    private List<SubMenu> subMenu;

    public Menu() {
    }

    public Menu(int id, String menuName, String path, String icon, List<SubMenu> subMenu) {
        this.id = id;
        this.menuName = menuName;
        this.path = path;
        this.icon = icon;
        this.subMenu = subMenu;
    }

    @Override
    public String toString() {
        return "Menu{" +
                "id=" + id +
                ", menuName='" + menuName + '\'' +
                ", path='" + path + '\'' +
                ", icon='" + icon + '\'' +
                ", subMenu=" + subMenu +
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

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public List<SubMenu> getSubMenu() {
        return subMenu;
    }

    public void setSubMenu(List<SubMenu> subMenu) {
        this.subMenu = subMenu;
    }
}
