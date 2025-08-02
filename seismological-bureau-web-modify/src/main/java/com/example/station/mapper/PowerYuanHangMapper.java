package com.example.station.mapper;

import com.example.station.entity.PowerYuanHang;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface PowerYuanHangMapper {
    @Select("select * from power_yuanhang where ups_id = #{powerId} order by dt desc limit 0,8")
    List<PowerYuanHang> firstGetSimpleData(String powerId);

    @Select("select * from power_yuanhang where ups_id = #{powerId} order by dt desc limit 0,1")
    PowerYuanHang getSimpleData(String powerId);
}
