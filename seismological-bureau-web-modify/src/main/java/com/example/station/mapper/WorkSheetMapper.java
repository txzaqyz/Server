package com.example.station.mapper;

import com.example.station.entity.WorkSheet;
import org.apache.ibatis.annotations.*;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

@Mapper
public interface WorkSheetMapper {
    @Insert("insert into worksheet (number, name,information,area_level,region_id,create_time, state, initiator) " +
            "values (#{number},#{name},#{information},#{areaLevel},#{regionId},#{createTime},#{state},#{initiator})")
    int creatWorkSheet(WorkSheet workSheet);

    @Update("update worksheet set confirm_time=#{confirmTime},handler=#{handler},state=#{state} where id=#{id}")
    int updateWorkSheet(int id, int handler, Timestamp confirmTime, int state);

    @Update("update worksheet set finish_time=#{finishTime},state=#{state} where id=#{id}")
    int endWorkSheet(int id, Timestamp finishTime, int state);

    @Select("select * from worksheet where id=#{id}")
    WorkSheet getOneWorkSheet(int id);

    @Delete("delete from worksheet where id=#{id}")
    int deleteWorkSheet(int id);

    @Result(column = "staff",property = "initiator")
    @Select("select t1.id,t1.number,t1.name,t1.information,t1.create_time,t2.staff,t3.address,t4.area " +
            "from worksheet t1,user t2,equipment_list t3,region t4 " +
            "where t2.id=t1.initiator and t1.state=#{state} and t3.number=t1.number and t4.id=t3.station_id")
    List<Map<String,Object>> getAllUnconfirmedWorkSheet(int state);

    @Select("select t1.id,t1.number,t1.name,t1.information,t1.create_time,t1.confirm_time,t2.staff as initiator,t3.staff as handler,t4.address,t5.area " +
            "from worksheet t1,user t2, user t3,equipment_list t4,region t5 " +
            "where t2.id=t1.initiator and t3.id=t1.handler and t1.state=#{state} and t4.number=t1.number and t5.id=t4.station_id")
    List<Map<String,Object>> getAllProcessingWorkSheet(int state);

    @Select("select t1.id,t1.number,t1.name,t1.information,t1.create_time,t2.staff as initiator,t3.address,t4.area " +
            "from worksheet t1,user t2,equipment_list t3,region t4 " +
            "where t2.id=t1.initiator and t1.state=#{state} and t1.region_id=#{regionId} and t3.number=t1.number and t1.region_id=t4.id")
    List<Map<String,Object>> getUnconfirmedWorkSheetByRegion(int state, int regionId);

    @Select("select t1.id,t1.number,t1.name,t1.information,t1.create_time,t1.confirm_time,t2.staff as initiator,t3.staff as handler,t4.address,t5.area " +
            "from worksheet t1,user t2, user t3,equipment_list t4,region t5 " +
            "where t2.id=t1.initiator and t3.id=t1.handler and t1.state=#{state} and t1.region_id=#{regionId} and t4.number=t1.number and t1.region_id=t5.id")
    List<Map<String,Object>> getProcessingWorkSheetByRegion(int state, int regionId);

    @Select("select t1.id,t1.number,t1.name,t1.information,t1.create_time,t1.confirm_time,t1.finish_time,t2.staff as initiator,t3.staff as handler,t4.address,t5.area " +
            "from worksheet t1,user t2, user t3,equipment_list t4,region t5 " +
            "where t2.id=t1.initiator and t3.id=t1.handler and t1.state=#{state} and t4.number=t1.number and t5.id=t4.station_id")
    List<Map<String,Object>> getHistoryRecord(int state);

    @Select("select t1.id,t1.number,t1.name,t1.information,t1.create_time,t1.confirm_time,t1.finish_time,t2.staff as initiator,t3.staff as handler,t4.address " +
            "from worksheet t1,user t2, user t3,equipment_list t4 " +
            "where t2.id=t1.initiator and t3.id=t1.handler and t1.state=#{state} and t4.number=t1.number and t1.region_id=#{regionId}")
    List<Map<String,Object>> getHistoryRecordByRegion(int state, int regionId);

    @Select("select t1.id,t1.number,t1.name,t1.information,t1.create_time,t2.staff as initiator,t3.address " +
            "from worksheet t1,user t2,equipment_list t3 " +
            "where t2.id=#{staffId} and t1.state=#{state} and t3.number=t1.number and t1.initiator=#{staffId}")
    List<Map<String,Object>> getPersonalUnconfirmedWorkSheet(int state, int staffId);

    @Select("select t1.id,t1.number,t1.name,t1.information,t1.create_time,t1.confirm_time,t2.staff as initiator,t3.staff as handler,t4.address " +
            "from worksheet t1,user t2, user t3,equipment_list t4 " +
            "where t2.id=t1.initiator and t3.id=t1.handler and t1.state=#{state} and t4.number=t1.number and (t1.initiator=#{staffId} or t1.handler=#{staffId})")
    List<Map<String,Object>> getPersonalProcessingWorkSheet(int state, int staffId);

    @Select("<script>"
    +"select count(*) from worksheet where region_id=#{regionId} and state=#{state}"
    +"<if test='month != null'>"
    +"and MONTH(finish_time)=#{month}"
    +"</if>"
    +"<if test='year != null'>"
    +"and YEAR(finish_time)=#{year}"
    +"</if>"
    +"</script>")
    int getCityDiffTypeWorkSheetCount(Integer regionId,Integer state,Integer month,Integer year);

    @Select("select region_id,count(*) num from worksheet where state=#{state} group by region_id")
    List<Map<String,Object>> getAllCityDiffTypeWorkSheetCount(Integer state);

    @Delete("delete from worksheet where number=#{number}")
    int deleteWorkSheetByNumber(String number);
//
//    @Select("select count(*) from worksheet where region_id=#{regionId} and state=#{state} and MONTH(finished_time)=month")
//    int getCityFinishedWorkSheetCount(int regionId,int state, int month);
}
