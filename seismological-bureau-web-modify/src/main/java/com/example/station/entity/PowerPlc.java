package com.example.station.entity;

//PLC的实体类，但是作为测试使用
public class PowerPlc {
    private int type;
    private String ts;
    private int val1;
    private int val2;
    private int val3;
    private String ups;
    private String mac;//存储其mac地址用以标识不同的plc

    public PowerPlc(){

    }

    public PowerPlc(int type, String ts, int val1, int val2, int val3, String ups, String mac) {
        this.type = type;
        this.ts = ts;
        this.val1 = val1;
        this.val2 = val2;
        this.val3 = val3;
        this.ups = ups;
        this.mac = mac;
    }

    @Override
    public String toString() {
        return "PowerPlc{" +
                "type=" + type +
                ", ts='" + ts + '\'' +
                ", val1=" + val1 +
                ", val2=" + val2 +
                ", val3=" + val3 +
                ", ups='" + ups + '\'' +
                ", mac='" + mac + '\'' +
                '}';
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getTs() {
        return ts;
    }

    public void setTs(String ts) {
        this.ts = ts;
    }

    public int getVal1() {
        return val1;
    }

    public void setVal1(int val1) {
        this.val1 = val1;
    }

    public int getVal2() {
        return val2;
    }

    public void setVal2(int val2) {
        this.val2 = val2;
    }

    public int getVal3() {
        return val3;
    }

    public void setVal3(int val3) {
        this.val3 = val3;
    }

    public String getUps() {
        return ups;
    }

    public void setUps(String ups) {
        this.ups = ups;
    }

    public String getMac() {
        return mac;
    }

    public void setMac(String mac) {
        this.mac = mac;
    }
}
