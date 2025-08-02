package com.example.station.service;

import com.example.station.entity.Equipment;
import com.example.station.entity.EquipmentRegister;
import com.example.station.utils.CommonResult;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

public interface EquipmentService {
    CommonResult addEquipment(Equipment equipment);

    CommonResult addEquipmentRegister(EquipmentRegister equipmentRegister);

    CommonResult updateEquipmentState(int id, int state,int staffId);

    CommonResult updateEquipmentInformation(Equipment equipment);

    CommonResult deleteEquipment(int id,int staffId);

    CommonResult<List<Equipment>> getEquipments(int regionId);

    CommonResult<List<Equipment>> getAllEquipments();

    String getCityEquipmentStatus(int cityId);

    String getEquipmentNumStatic();

    String getEquipmentInNumYear();

    String getEquipmentCountThroughStatus();

    public Map<String,Object> getPatrolStatus(Integer cityId);

    public String getPatrolStatus(Integer cityId,Integer areaId);

    public List<Map<String,Object>> getPatrolStatus();

    CommonResult fullDeleteEquipment(String number);

    String getEquipmentNameByStation(int cityId,int areaId);

    String getEquipmentNumberByStation(int cityId,int areaId,String name);

    String getAllEquipmentInfo(int cityId, int areaId, int type);

    List<Map<String,Object>> getDataExpiredEquipment(Integer cityId, Integer areaId, Integer type);

    CommonResult addNewMarker(Integer regionId,Integer areaId,Integer MarkerType,Float longitude,Float latitude);

    String getMarkerInfo(Integer regionId,Integer areaId);

    CommonResult clearMarker(Integer regionId,Integer areaId);

    CommonResult uploadPicture(MultipartFile image, Integer region_id, Integer area_id);

    String getPictureURL(Integer regionID,Integer areaID);

    void deleteIMG(String imagePath);

//  逻辑修改了，这里暂时用不上
//    List<Map<String,Object>> getPowerEquipmentRecentInfoTime(Integer cityId, Integer areaId);
//
//    List<Map<String,Object>> getPlcEquipmentRecentInfoTime(Integer cityId, Integer areaId);
}
