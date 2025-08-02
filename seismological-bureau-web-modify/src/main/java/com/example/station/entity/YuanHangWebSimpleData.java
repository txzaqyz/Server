package com.example.station.entity;

/**
 * 网页显示电源信息实体类
 */

public class YuanHangWebSimpleData {
    private double AcVoltage;
    private double powerTemperature;
    private double batteryA;
    private double batteryB;
    private double temperature;
    private double humidity;
    private int alarm;
    private String time;

    public YuanHangWebSimpleData() {
    }

    public YuanHangWebSimpleData(double acVoltage, double powerTemperature, double batteryA, double batteryB, double temperature, double humidity, int alarm, String time) {
        AcVoltage = acVoltage;
        this.powerTemperature = powerTemperature;
        this.batteryA = batteryA;
        this.batteryB = batteryB;
        this.temperature = temperature;
        this.humidity = humidity;
        this.alarm = alarm;
        this.time = time;
    }


    @Override
    public String toString() {
        return "YuanHangWebSimpleData{" +
                "AcVoltage=" + AcVoltage +
                ", powerTemperature=" + powerTemperature +
                ", batteryA=" + batteryA +
                ", batteryB=" + batteryB +
                ", temperature=" + temperature +
                ", humidity=" + humidity +
                ", alarm=" + alarm +
                ", time='" + time + '\'' +
                '}';
    }

    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    public double getHumidity() {
        return humidity;
    }

    public void setHumidity(double humidity) {
        this.humidity = humidity;
    }

    public double getAcVoltage() {
        return AcVoltage;
    }

    public void setAcVoltage(double acVoltage) {
        AcVoltage = acVoltage;
    }

    public double getPowerTemperature() {
        return powerTemperature;
    }

    public void setPowerTemperature(double powerTemperature) {
        this.powerTemperature = powerTemperature;
    }

    public double getBatteryA() {
        return batteryA;
    }

    public void setBatteryA(double batteryA) {
        this.batteryA = batteryA;
    }

    public double getBatteryB() {
        return batteryB;
    }

    public void setBatteryB(double batteryB) {
        this.batteryB = batteryB;
    }

    public int getAlarm() {
        return alarm;
    }

    public void setAlarm(int alarm) {
        this.alarm = alarm;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
