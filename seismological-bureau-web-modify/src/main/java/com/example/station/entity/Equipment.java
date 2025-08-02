package com.example.station.entity;

import com.example.station.annotation.CreateTime;
import com.example.station.annotation.UpdateTime;


import java.sql.Timestamp;

/**
 * 设备实体类
 */
public class Equipment {
    private int id;
    private String number;
    private String name;
    private String address;
    private int stationId;
    private String ip;
//    因目前没有能控制的设备，该项暂时保留，默认均为0，即启用
    private int enable;
    private int state;
    @CreateTime
    private Timestamp createTime;
    @UpdateTime
    private Timestamp updateTime;
    private int maintenance;
    private int operator;
    //
    private int equipmentType;
    private String equipmentInfo;
    private int areaId;

    private Boolean connectionStatus;
    private Boolean dataExpired;





        /**
         * 实体类参数说明键
         *      * @param number     设备编号
         *      * @param name       设备名
         *      * @param address    设备地址
         *      * @param stationId  城市id
         *      * @param ip         设备ip
         *      * @param enable     表示是否加入巡检，0表示未加入，不对该设备进行网络巡检；1表示加入巡检，将会对设备进行网络巡检
         *      * @param state      设备运行状态：正常0，故障/维修1, 报废2
         *      * @param createTime 添加设备的时间
         *      * @param updateTime 最后一次变更设备状态操作的时间
         *      * @param maintenance 设备维修次数（暂时保留）
         *      * @param operator    该设备最后的操作人
         * @param   id         数据库主
         * @param equipmentType 设备类型(1：摄像头 2：智能电源 3：PLC)
         * @param equipmentInfo 设备标识符（摄像头就是rtsp地址、智能电源就是ups_id、PLC就是mac地址）
         * @param  areaId 城市内的区域Id
         */
    public Equipment(int id, String number, String name, String address, int stationId, String ip, int enable, int state, Timestamp createTime, Timestamp updateTime, int maintenance, int operator, int equipmentType, String equipmentInfo, int areaId, Boolean connectionStatus, Boolean dataExpired) {
        this.id = id;
        this.number = number;
        this.name = name;
        this.address = address;
        this.stationId = stationId;
        this.ip = ip;
        this.enable = enable;
        this.state = state;
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.maintenance = maintenance;
        this.operator = operator;
        this.equipmentType = equipmentType;
        this.equipmentInfo = equipmentInfo;
        this.areaId = areaId;
        this.connectionStatus = connectionStatus;
        this.dataExpired = dataExpired;
    }
    @Override
    public String toString() {
        return "Equipment{" +
                "id=" + id +
                ", number='" + number + '\'' +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", stationId=" + stationId +
                ", ip='" + ip + '\'' +
                ", enable=" + enable +
                ", state=" + state +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", maintenance=" + maintenance +
                ", operator=" + operator +
                ", equipmentType=" + equipmentType +
                ", equipmentInfo='" + equipmentInfo + '\'' +
                ", areaId=" + areaId +
                ", connectionStatus=" + connectionStatus +
                ", dataExpired=" + dataExpired +
                '}';
    }


    public int getOperator() {
        return operator;
    }

    public void setOperator(int operator) {
        this.operator = operator;
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

    public int getStationId() {
        return stationId;
    }

    public void setStationId(int stationId) {
        this.stationId = stationId;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public int getEnable() {
        return enable;
    }

    public void setEnable(int enable) {
        this.enable = enable;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp creatTime) {
        this.createTime = creatTime;
    }

    public Timestamp getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Timestamp updateTime) {
        this.updateTime = updateTime;
    }

    public int getMaintenance() {
        return maintenance;
    }

    public void setMaintenance(int maintenance) {
        this.maintenance = maintenance;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getEquipmentType() {
        return equipmentType;
    }

    public void setEquipmentType(int equipmentType) {
        this.equipmentType = equipmentType;
    }

    public String getEquipmentInfo() {
        return equipmentInfo;
    }

    public void setEquipmentInfo(String equipmentInfo) {
        this.equipmentInfo = equipmentInfo;
    }

    public int getAreaId() {
        return areaId;
    }

    public void setAreaId(int areaId) {
        this.areaId = areaId;
    }

    public Boolean getConnectionStatus() {
        return connectionStatus;
    }

    public void setConnectionStatus(Boolean connectionStatus) {
        this.connectionStatus = connectionStatus;
    }

    public Boolean getDataExpired() {
        return dataExpired;
    }

    public void setDataExpired(Boolean dataExpired) {
        this.dataExpired = dataExpired;
    }

}
