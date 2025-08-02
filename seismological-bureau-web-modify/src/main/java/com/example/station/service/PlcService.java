package com.example.station.service;

import com.example.station.entity.PowerPlc;

public interface PlcService {//开放给前端的，写入plc数据的部分就不写在这里了
    String getPlcData(int type,String mac);
}
