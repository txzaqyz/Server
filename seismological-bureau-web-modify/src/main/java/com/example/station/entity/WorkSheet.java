package com.example.station.entity;

import com.example.station.annotation.CreateTime;

import java.sql.Timestamp;

/**
 * 工单实体类
 */

public class WorkSheet {
    private int id;
    private String number;
    private String name;
    private String information;
    private int areaLevel;
    private int regionId;
    @CreateTime
    private Timestamp createTime;
    private Timestamp confirmTime;
    private Timestamp finishTime;
    private int state;
    private int initiator;
    private int handler;

    /**
     *
     * @param id   工单编号
     * @param number   设备编号
     * @param name    设备名称
     * @param information   故障信息
     * @param areaLevel     地区权限
     * @param regionId      地区编号
     * @param createTime    创建时间
     * @param confirmTime   确认时间
     * @param finishTime    完成时间
     * @param state         工单状态， 0待确认，1已确认/待完成，2完成
     * @param initiator     工单创建人
     * @param handler       工单处理人
     */
    public WorkSheet(int id, String number, String name, String information, int areaLevel, int regionId, Timestamp createTime, Timestamp confirmTime, Timestamp finishTime, int state, int initiator, int handler) {
        this.id = id;
        this.number = number;
        this.name = name;
        this.information = information;
        this.areaLevel = areaLevel;
        this.regionId = regionId;
        this.createTime = createTime;
        this.confirmTime = confirmTime;
        this.finishTime = finishTime;
        this.state = state;
        this.initiator = initiator;
        this.handler = handler;
    }

    public WorkSheet() {
    }

    @Override
    public String toString() {
        return "WorkSheet{" +
                "id=" + id +
                ", number='" + number + '\'' +
                ", name='" + name + '\'' +
                ", information='" + information + '\'' +
                ", areaLevel=" + areaLevel +
                ", regionId=" + regionId +
                ", createTime=" + createTime +
                ", confirmTime=" + confirmTime +
                ", finishTime=" + finishTime +
                ", state=" + state +
                ", initiator=" + initiator +
                ", handler=" + handler +
                '}';
    }

    public String getInformation() {
        return information;
    }

    public void setInformation(String information) {
        this.information = information;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public Timestamp getConfirmTime() {
        return confirmTime;
    }

    public void setConfirmTime(Timestamp confirmTime) {
        this.confirmTime = confirmTime;
    }

    public Timestamp getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(Timestamp finishTime) {
        this.finishTime = finishTime;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public int getInitiator() {
        return initiator;
    }

    public void setInitiator(int initiator) {
        this.initiator = initiator;
    }

    public int getHandler() {
        return handler;
    }

    public void setHandler(int handler) {
        this.handler = handler;
    }

    public int getAreaLevel() {
        return areaLevel;
    }

    public void setAreaLevel(int areaLevel) {
        this.areaLevel = areaLevel;
    }

    public int getRegionId() {
        return regionId;
    }

    public void setRegionId(int regionId) {
        this.regionId = regionId;
    }

}
