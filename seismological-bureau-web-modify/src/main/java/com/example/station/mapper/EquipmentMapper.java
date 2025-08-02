package com.example.station.mapper;

import com.example.station.entity.Equipment;
import com.example.station.entity.EquipmentRegister;
import com.example.station.entity.Marker;
import org.apache.ibatis.annotations.*;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

@Mapper
public interface EquipmentMapper {
    @Insert("insert into equipment_list (number,name,address,station_id,ip,enable,state,create_time,maintenance,operator,equipment_type,equipment_info,area_id) values (" +
            "#{number},#{name},#{address},#{stationId},#{ip},#{enable},#{state},#{createTime},#{maintenance},#{operator},#{equipmentType},#{equipmentInfo},#{areaId})")
    int addEquipment(Equipment equipment);

    @Insert("INSERT INTO equipment_register (number,device_code,observe_code,type_code,device_model,brand,serial,version,life,is_import,is_final,is_produce,produce_time,warranty_time," +
            "manu_code,manu_name,manu_address,manu_zip,manu_contact,manu_phone,manu_email," +
            "sup_code,sup_name,sup_zip,sup_contact,sup_phone,sup_email,operator) values ("+
            "#{number},#{device_code},#{observe_code},#{type_code},#{device_model},#{brand},#{serial},#{version},#{life},#{is_import},#{is_final},#{is_produce},#{produce_time},#{warranty_time}," +
            "#{manu_code},#{manu_name},#{manu_address},#{manu_zip},#{manu_contact},#{manu_phone},#{manu_email}," +
            "#{sup_code},#{sup_name},#{sup_zip},#{sup_contact},#{sup_phone},#{sup_email},#{operator})")
    int addEquipmentRegister(EquipmentRegister equipmentRegister);


    @Select("select count(number) from equipment_list where number=#{number}")
    int checkEquipmentNumber(String number);

    @Select("select count(number) from equipment_register where number=#{number}")
    int checkEquipmentRegisterNumber(String number);

    @Update("update equipment_list set state=#{state}, update_time=#{updateTime},operator=#{operator} where id=#{id}")
    int updateEquipmentStateById(int id, int state, Timestamp updateTime, int operator);

    @Update("update equipment_list set state=#{state}, update_time=#{updateTime} ,operator=#{operator} where number=#{number}")
    int updateEquipmentStateByNumber(String number, int state, Timestamp updateTime, int operator);

    @Update("update equipment_list set number=#{number},name=#{name},station_id=#{stationId},address=#{address},ip=#{ip},update_time=#{updateTime},operator=#{operator},equipment_type=#{equipmentType},equipment_info=#{equipmentInfo},area_id=#{areaId},enable=#{enable} where id=#{id}")
    int updateEquipmentInformation(Equipment equipment);

    @Result(column = "staff",property = "operator")
    @Select("select t1.id,t1.number,t1.name,t1.address,t1.ip,t1.state,t1.create_time,t1.update_time,t2.staff,t1.station_id,t3.area,t1.equipment_type,t1.equipment_info,t1.area_id,t1.enable,t1.data_expired from equipment_list t1,user t2,region t3 where t2.id=t1.operator and t3.id=t1.station_id")
    List<Map<String,Object>> getAllEquipment();

    @Result(column = "staff",property = "operator")
    @Select("select t1.id,t1.number,t1.name,t1.address,t1.ip,t1.state,t1.create_time,t1.update_time,t2.staff,t1.station_id,t3.area,t1.equipment_type,t1.equipment_info,t1.area_id,t1.enable,t1.data_expired from equipment_list t1,user t2,region t3 where t2.id=t1.operator and t3.id=t1.station_id and station_id=#{stationId}")
    List<Map<String,Object>> getEquipment(int stationId);

    @Select("select * from equipment_list where state=0 and enable=0")
    @ResultMap(value = {"equipmentMap"})
    List<Equipment> getDetectionEquipment();

    @Select("select count(*) from equipment_list where station_id=#{cityId} and state=#{state}")
    int getEquipmentStatus(int cityId,int state);

    @Select("select count(*) from equipment_list where station_id=#{cityId} and area_id=#{areaId}")
    int getCityEquipmentNum(int cityId,int areaId);

    @Select("select state,count(*) num from equipment_list GROUP BY state")
    List<Map<Integer,Integer>> getEquipmentCountByStatus();

    @Select("select station_id,count(*) num from equipment_list where state!=2 GROUP BY station_id")
    List<Map<Integer,Integer>> getAllCityEquipmentNum();

    @Select("select station_id,count(*) num from equipment_list where YEAR(create_time)=#{year} GROUP BY station_id")
    List<Map<Integer,Integer>> getAllCityEquipmentYear(int year);


    @Select("select * from equipment_list where station_id=#{cityId} and enable=1 and (state=1 or state=0)")
    @Results(id="equipmentMap",value={
            @Result(column = "staff",property = "operator"),
            @Result(column = "area_id",property = "areaId"),
            @Result(column = "equipment_info",property = "equipmentInfo"),
            @Result(column = "equipment_type",property = "equipmentType"),
            @Result(column = "connection_status",property = "connectionStatus"),
            @Result(column = "create_time",property = "createTime"),
            @Result(column = "update_time",property = "updateTime"),
            @Result(column = "station_id",property = "stationId")
    })
    List<Equipment> getPatrolDevice(int cityId);

    @Update("update equipment_list set connection_status=#{status} where id=#{id}")
    int updateConnectionStatus(int id,boolean status);

    @Update("update equipment_list set data_expired=#{isDataExpired} where id=#{id}")
    int updateIsDataExpired(int id,boolean isDataExpired);

    @Select("select * from equipment_list where station_id=#{cityId} and enable=1 and (state=1 or state=0) order by area_id")
    @ResultMap(value = {"equipmentMap"})
    List<Equipment> getPatrolStatus(int cityId);

    @Select("select * from equipment_list where station_id=#{cityId} and area_id=#{areaId} and enable=1 and (state=1 or state=0) order by id")
    @ResultMap(value = {"equipmentMap"})
    List<Equipment> getAreaPatrolStatus(int cityId,int areaId);

    @Select("select equipment_info from equipment_list where station_id=#{cityId} and area_id=#{areaId} and equipment_type=#{type} and (state=1 or state=0) and enable=1 order by id")
    List<String> getAreaPatrolDeviceByType(int cityId, int areaId, int type);

    @Select("select equipment_info rtspAddress,ip,connection_status connectionStatus,number from equipment_list where station_id=#{cityId} and area_id=#{areaId} and equipment_type=1 and (state=1 or state=0) and enable=1 order by id")
    List<Map<String,Object>> getAreaPatrolDeviceOfCamera(int cityId, int areaId);


    @Delete("delete from equipment_list where number=#{number}")
    int fullDeleteEquipment(String number);

    @Select("select name from equipment_list where station_id=#{cityId} and area_id=#{areaId}")
    List<String> getEquipmentNameByStation(int cityId,int areaId);

    @Select("select number from equipment_list where station_id=#{cityId} and area_id=#{areaId} and name=#{name}")
    List<String> getEquipmentNumberByStation(int cityId,int areaId,String name);

    @Select("<script>"
        +"select e.number,e.station_id cityId,e.equipment_type equipmentType,e.equipment_info equipmentInfo,e.area_id areaId,e.data_expired dataExpired "
        +"FROM equipment_list e "
        +"WHERE e.state!=2 and e.data_expired=1 "
        +"<if test='areaId != null'> "
        +"and e.area_id=#{areaId} "
        +"</if> "
        +"<if test='cityId != null'> "
        +"and e.station_id=#{cityId} "
        +"</if> "
        +"<if test='type != null'> "
        +"and e.equipment_type=#{type} "
        +"</if> "
        +"</script> ")
    List<Map<String,Object>> getDataExpiredEquipment(Integer cityId, Integer areaId, Integer type);

    @Update("update equipment_list e " +
            "LEFT JOIN (select Max(p.ts) newest_time, p.mac mac " +
            "FROM power_plc p GROUP BY p.mac) plcTable " +
            "ON e.equipment_info=plcTable.mac " +
            "SET e.data_expired=IFNULL(TIMESTAMPDIFF(MINUTE,plcTable.newest_time,CURRENT_TIMESTAMP())>5,1) " +
            "WHERE e.equipment_type=#{type}")
    int updateDataExpiredStatusOfPlc(Integer type);

    @Update("update equipment_list e " +
            "LEFT JOIN (select Max(p.dt) newest_time, p.ups_id ups_id " +
            "FROM power_yuanhang p GROUP BY p.ups_id) powerTable " +
            "ON e.equipment_info=powerTable.ups_id " +
            "SET e.data_expired=IFNULL(TIMESTAMPDIFF(MINUTE,powerTable.newest_time,CURRENT_TIMESTAMP())>5,1) " +
            "WHERE e.equipment_type=#{type}")
    int updateDataExpiredStatusOfPower(Integer type);

    @Update("update station set camera_latitude=#{latitude},camera_longitude=#{longitude} where region_id=#{regionId} and area_id=#{areaId}")
    int addCameraMarker(int regionId,int areaId, float longitude,float latitude);

    @Update("update station set power_latitude=#{latitude},power_longitude=#{longitude} where region_id=#{regionId} and area_id=#{areaId}")
    int addPowerMarker(int regionId,int areaId, float longitude,float latitude);

    @Select("select camera_latitude,camera_longitude,power_latitude,power_longitude from station where  region_id=#{regionId} and area_id=#{areaId}")
    @Results(id="MarkerMap",value={
            @Result(column = "camera_latitude",property = "camera_latitude"),
            @Result(column = "camera_longitude",property = "camera_longitude"),
            @Result(column = "power_latitude",property = "power_latitude"),
            @Result(column = "power_longitude",property = "power_longitude"),
    })
    Marker getMarkerInfo(int regionId, int areaId);

    @Update("update station set power_latitude=8888,power_longitude=8888,camera_latitude=8888,camera_longitude=8888 where   region_id=#{regionId} and area_id=#{areaId}")
    int clearMarker(int regionId,int areaId);





    @Insert("UPDATE station SET picture = #{lujin} where region_id=#{regionID} and area_id=#{areaID}")
    int uploadPicture(String lujin,int regionID,int areaID);

    @Select(("select picture from station where area_id=#{areaID} and region_id =#{regionID}"))
    String getPictureURL(int regionID, int areaID);
    //暂时无用
//    @Select("<script>"
//            +"select  e.number,IFNULL(TIMESTAMPDIFF(MINUTE,MAX(p.dt),CURRENT_TIMESTAMP())>5,1) dataRecentTime "
//            +"FROM equipment_list e LEFT JOIN power_yuanhang p "
//            +"ON e.equipment_info=p.ups_id "
//            +"WHERE e.equipment_type=2 and e.station_id=#{cityId} and e.state!=2 "
//            + "<if test='areaId != null'> "
//            + "and e.area_id=#{areaId} "
//            +"</if> "
//            +"GROUP BY e.number "
//            +"</script> ")
//    List<Map<String,Object>> getPowerEquipmentRecentInfoTime(Integer cityId, Integer areaId);
//
//    @Select("<script>"
//            +"select e.number,IFNULL(TIMESTAMPDIFF(MINUTE,MAX(p.ts),CURRENT_TIMESTAMP())>5,1) dataRecentTime "
//            +"FROM equipment_list e LEFT JOIN power_plc p "
//            +"ON e.equipment_info=p.mac "
//            +"WHERE e.equipment_type=3 and e.station_id=#{cityId} and e.state!=2 "
//            + "<if test='areaId != null'> "
//            + "and e.area_id=#{areaId} "
//            +"</if> "
//            +"GROUP BY e.number "
//            +"</script> ")
//    List<Map<String,Object>> getPlcEquipmentRecentInfoTime(Integer cityId, Integer areaId);
}
