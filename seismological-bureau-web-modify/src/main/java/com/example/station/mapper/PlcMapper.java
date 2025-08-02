package com.example.station.mapper;

import com.example.station.entity.PowerPlc;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface PlcMapper {

    @Insert("insert into power_plc (type,ts,val1,val2,val3,mac) values(" + "#{type},#{ts},#{val1},#{val2},#{val3},#{mac})")
    int addPowerPlc(PowerPlc powerPlc);

    @Insert("insert into power_plc (type,ts,val1,mac) values(" + "#{type},#{ts},#{val1},#{mac})")
    int addPowerPlcDi(PowerPlc powerPlc);

    @Insert("insert into power_plc (type,ts,val1,mac) values(" + "#{type},#{ts},#{val1},#{mac})")
    int addPowerPlcACK(PowerPlc powerPlc);

    @Insert("insert into power_plc (type,ts,val1,val2,mac) values(" + "#{type},#{ts},#{val1},#{val2},#{mac})")
    int addPowerPlcTemperatureAndHumidity(PowerPlc powerPlc);

    @Insert("insert into power_plc (type,ts,ups,mac) values(" + "#{type},#{ts},#{ups},#{mac})")
    int addPowerPlcUps(PowerPlc powerPlc);

    @Select("select * from power_plc where id = #{plcId} order by id desc limit 0,8")
    List<PowerPlc> getDataTest(String plcId);

    @Select("select * from power_plc where type = #{type} and mac = #{mac} order by id desc limit 0,1")
    PowerPlc getPlcDataOneTime(int type, String mac);
}
