package com.example.station.entity;

import com.example.station.annotation.CreateTime;

import java.sql.Timestamp;

/**
 * 用户实体类
 */

public class User {
    private int id;
    private String username;
    private String password;
    private String staff;
    private int authority;
    private int area_level;
    private int region_id;
    @CreateTime
    private Timestamp create_time;
    private Timestamp update_time;
    private int state;

    public User(int id, String username, String password, String staff, int authority, int area_level, int region_id, Timestamp create_time, Timestamp update_time, int state) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.staff = staff;
        this.authority = authority;
        this.area_level = area_level;
        this.region_id = region_id;
        this.create_time = create_time;
        this.update_time = update_time;
        this.state = state;
    }

    public User() {
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", staff='" + staff + '\'' +
                ", authority=" + authority +
                ", area_level=" + area_level +
                ", region_id=" + region_id +
                ", create_time=" + create_time +
                ", update_time=" + update_time +
                ", state=" + state +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getStaff() {
        return staff;
    }

    public void setStaff(String staff) {
        this.staff = staff;
    }

    public int getAuthority() {
        return authority;
    }

    public void setAuthority(int authority) {
        this.authority = authority;
    }

    public int getArea_level() {
        return area_level;
    }

    public void setArea_level(int area_level) {
        this.area_level = area_level;
    }

    public Timestamp getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Timestamp creat_time) {
        this.create_time = creat_time;
    }

    public Timestamp getUpdate_time() {
        return update_time;
    }

    public void setUpdate_time(Timestamp update_time) {
        this.update_time = update_time;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public int getRegion_id() {
        return region_id;
    }

    public void setRegion_id(int region_id) {
        this.region_id = region_id;
    }
}
