package com.example.station.mapper;

import com.example.station.entity.WorkSheetNum;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface WorkSheetQuantityMapper {
    @Update("update worksheet_quantity set worksheet_quantity=worksheet_quantity+1 where cityId=#{cityId} and month=#{month}")
    int increaseWorkSheetQuantity(int cityId,int month);

    @Update("update worksheet_quantity set worksheet_quantity=0 where cityId=#{cityId} and month=#{month}")
    int updateWorkSheetQuantity(int cityId,int month);

    @Update("update worksheet_quantity set year=#{year} where cityId=#{cityId} and month=#{month}")
    int updateWorkSheetYear(int year,int cityId,int month);

    @Select("select year from worksheet_quantity where cityId=#{cityId} and month=#{month}")
    int getYearOfWorksheetQuantity(int cityId,int month);

    @Select("select * from worksheet_quantity where cityId=#{cityId}")
    List<WorkSheetNum> getCityWorkSheetAllMonth(int cityId);
}
