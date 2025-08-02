package com.example.station.mapper;

import com.example.station.entity.Region;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * 1、大概应该是暂时没用的，定时巡检不用这个。(可以当作没有，暂时不删除)
 * 2、有关station的操作和region的操作现在暂时都放在MenuAndStationMapper中
 */
@Mapper
public interface StationMapper {
    @Select("select * from station where id=#{id}")
    Region getEquipmentAuthority(int id);
}
