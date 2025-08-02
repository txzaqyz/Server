package com.example.station.mapper;

import com.example.station.entity.Area;
import com.example.station.entity.Station;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface MenuAndStationMapper {
    @Select("select * from region")
    List<Area> getArea();

    @Select("select count(*) from region")
    int getRegionNum();

    @Select("select * from station")
    List<Station> getStation();

    @Select("select * from station where region_id=#{regionId}")
    List<Station> getStationByRegion(int regionId);

    @Insert("insert into station (address,level,region_id,area_id,coordinate_x,coordinate_y,is_center,camera_latitude,camera_longitude,power_latitude,power_longitude) values (#{address},#{level},#{regionId},(select IFNULL(max(area_id),-1)+1 from station s where region_id=#{regionId}),#{coordinate_x},#{coordinate_y},(select IF(IFNULL(max(area_id),-1)>=0,0,1) from station ss where region_id=#{regionId}),8888,8888,8888,8888)")
    int addStation(String address, int level, int regionId,double coordinate_x,double coordinate_y);

    @Select("select count(address) from station where address=#{address} and region_id=#{regionId}")
    int checkStation(String address,int regionId);

    @Delete("delete from station where region_id=#{regionId} and area_id=#{areaId}")
    int deleteStation(int regionId, int areaId);

    @Select("select is_center from station where area_id=#{areaId} and region_id=#{regionId}")
    boolean checkStationCenter(int regionId,int areaId);

    @Select("select count(*) from station where region_id=#{regionId}")
    int checkStationCount(int regionId);

    @Select("select img_url from region where id=#{regionId}")
    String getCityPicture(int regionId);
}