package com.example.station.utils;

import com.alibaba.fastjson.JSON;
import com.example.station.entity.PowerPlc;
import com.example.station.mapper.PlcMapper;

import javax.annotation.Resource;

/**
 * 接收plc信息的工具
 * */
public class PlcMsgUtils {
    public static final int PLC_DATA_TYPE_UP_DI = 0;
    public static final int PLC_DATA_TYPE_UP_AI = 1;
    public static final int PLC_DATA_TYPE_UP_ACK = 2;
    public static final int PLC_DATA_TYPE_UP_TEMPERATRUE_AND_HUMIDITY = 3;
    public static final int PLC_DATA_TYPE_UP_UPS = 4;

    /**
     * 根据获得的数据和话题创建实体类
     * */
    public static PowerPlc creatPlcEntity(String topic,String plcMsg){
        PowerPlc res = JSON.parseObject(plcMsg,PowerPlc.class);
        String[] topicLayer = topic.split("\\.|/");
        if(topicLayer.length != 3 || topicLayer[1].length() != 12){
            System.out.println("mqtt topic "+topic+" ERROR");
            return null;
        }
        res.setMac(topicLayer[1]);
        return res;
    }

    /**
     * 根据实体类将不同type的数据写入数据库
     * */
    public static boolean addPlcMsg(PlcMapper plcMapper,PowerPlc powerPlc){
        int addFlag = 0;
        if(powerPlc.getType() == PLC_DATA_TYPE_UP_DI){
            addFlag = plcMapper.addPowerPlcDi(powerPlc);
        }else if(powerPlc.getType() == PLC_DATA_TYPE_UP_AI){
            //暂未使用
        }else if(powerPlc.getType() == PLC_DATA_TYPE_UP_ACK){
            addFlag = plcMapper.addPowerPlcACK(powerPlc);
        }else if(powerPlc.getType() == PLC_DATA_TYPE_UP_TEMPERATRUE_AND_HUMIDITY){
//            System.out.println(powerPlc.toString());
            addFlag = plcMapper.addPowerPlcTemperatureAndHumidity(powerPlc);
        }else if(powerPlc.getType() == PLC_DATA_TYPE_UP_UPS){
            addFlag = plcMapper.addPowerPlcUps(powerPlc);
        }
        return addFlag == 0 ? false:true;
    }
}
