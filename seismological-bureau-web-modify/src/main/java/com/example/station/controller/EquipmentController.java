package com.example.station.controller;

import com.example.station.annotation.CheckOperationAuthority;
import com.example.station.entity.Equipment;
import com.example.station.entity.EquipmentRegister;
import com.example.station.menu.AreaLevelConst;
import com.example.station.service.impl.EquipmentServiceImpl;
import com.example.station.utils.CommonResult;
import com.example.station.utils.JSONUtils;
import com.example.station.utils.JWTUtils;
import com.example.station.utils.rtspTest;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class EquipmentController {
    @Resource
    private EquipmentServiceImpl equipmentService;

    /**
     * 添加设备
     * @param request
     * @param equipment 设备的实体类
     * @return
     */
    @CheckOperationAuthority
    @PostMapping("/equipment/addEquipment")
    public CommonResult addEquipment(HttpServletRequest request,
                                     @RequestBody Equipment equipment){
//        System.out.println(request);
        int staffId = JWTUtils.parseId(JWTUtils.parseToken(request));
        equipment.setOperator(staffId);
//        System.out.println(equipment.toString());
        return equipmentService.addEquipment(equipment);
    }

    @CheckOperationAuthority
    @PostMapping("/equipment/registerEquipment")
    public CommonResult registerEquipment(HttpServletRequest request,
                                          @RequestBody EquipmentRegister equipmentRegister){
        System.out.println(request);
        int staffId = JWTUtils.parseId(JWTUtils.parseToken(request));
        equipmentRegister.setOperator(staffId);
        System.out.println(equipmentRegister.toString());
       return equipmentService.addEquipmentRegister(equipmentRegister);
    }
    /**
     * 变更设备状态
     * @param request
     * @param id   设备id
     * @param state   设备状态
     * @return
     */
    @CheckOperationAuthority
    @PostMapping("/equipment/updateEquipmentState")
    public CommonResult updateEquipmentState(HttpServletRequest request,
                                  @RequestParam(value = "id", required = true) int id,
                                  @RequestParam(value = "state", required = true) int state) {
        int staffId = JWTUtils.parseId(JWTUtils.parseToken(request));
        return equipmentService.updateEquipmentState(id,state,staffId);
    }

    /**
     * 更新设备信息
     * @param request
     * @param equipment  设备实体类
     * @return
     */
    @CheckOperationAuthority
    @PostMapping("/equipment/updateEquipmentInformation")
    public CommonResult updateEquipmentInformation(HttpServletRequest request,
                                             @RequestBody Equipment equipment) {
        int staffId = JWTUtils.parseId(JWTUtils.parseToken(request));
        equipment.setOperator(staffId);
        return equipmentService.updateEquipmentInformation(equipment);
    }


    /**
     * 删除设备
     * @param request
     * @param id
     * @return
     */
    @CheckOperationAuthority
    @PostMapping("/equipment/deleteEquipment")
    public CommonResult deleteEquipment(HttpServletRequest request,
                                  @RequestParam(value = "id", required = true) int id){
        int staffId = JWTUtils.parseId(JWTUtils.parseToken(request));
        return equipmentService.deleteEquipment(id,staffId);
    }

    /**
     * 彻底删除设备(因为设置了batch，无法获得返回行，这里直接返回删除成功。。。)
     */
    @CheckOperationAuthority
    @PostMapping("/equipment/fullDeleteEquipment")
    public CommonResult fullDeleteEquipment(HttpServletRequest request,
                                        @RequestParam(value = "number", required = true) String number){
        return equipmentService.fullDeleteEquipment(number);
    }

    /**
     * 获取设备列表
     * @param request
     * @return
     */
    @CheckOperationAuthority
    @PostMapping("/equipment/getEquipments")
    public CommonResult<List<Equipment>> getEquipments(HttpServletRequest request){
        String token = JWTUtils.parseToken(request);
        int regionId = JWTUtils.parseRegionId(token);
        int areaLevel = JWTUtils.parseAreaLevel(token);
        if (areaLevel == AreaLevelConst.PROVINCE) {
            return equipmentService.getAllEquipments();
        } else {
            return equipmentService.getEquipments(regionId);
        }
    }

    /**
     * 获取正常工作和故障的设备的数量
     * @param cityId 对应的城市id
     * @return
     */
    @PostMapping("/equipment/getCityEquipmentStatus")
    public String getCityEquipmentStatus(int cityId){
        return equipmentService.getCityEquipmentStatus(cityId);
    }

    /**
     * 获取不同区域设备总数
     * @return
     */
    @PostMapping("/equipment/getEquipmentNumStatic")
    public String getEquipmentNumStatic(){
        return equipmentService.getEquipmentNumStatic();
    }

    /**
     * 获取不同区域今年添加的设备总数
     * @return
     */
    @PostMapping("/equipment/getEquipmentInNumYear")
    public String getEquipmentInNumYear(){
        return equipmentService.getEquipmentInNumYear();
    }

    @PostMapping("/equipment/getEquipmentCountThroughStatus")
    public String getEquipmentCountThroughStatus(){
        return equipmentService.getEquipmentCountThroughStatus();
    }

    /**
     * 这是展示巡检状态详情
     * @param cityId
     * @return
     */
    @PostMapping("/equipment/getPatrolStatus")
    public String getPatrolStatus(@RequestParam(value = "cityId" , required = false) Integer cityId,
                                  @RequestParam(value="areaId",required = false) Integer areaId){
        if(cityId != null && areaId != null){
            return equipmentService.getPatrolStatus(cityId,areaId);
        }else if(cityId != null){
            return JSONUtils.toJsonString(equipmentService.getPatrolStatus(cityId));
        }else{
            return JSONUtils.toJsonString(equipmentService.getPatrolStatus());
        }
    }

    /**
     *
     * @param cityId 城市Id
     * @param areaId 区域Id
     * @param type 设备类型
     * @return
     */
    @PostMapping("/equipment/getAllEquipmentInfo")
    public String getAllEquipmentInfo(int cityId, int areaId, int type){
        return equipmentService.getAllEquipmentInfo(cityId, areaId, type);
    }

    /**
     *获取台站内的设备名，于工单的多选择框联动
     */
    @PostMapping("/equipment/getEquipmentNameByStation")
    public String getEquipmentNameByStation(@RequestParam(value = "cityId" , required = true) Integer cityId,
                                            @RequestParam(value="areaId",required = true) Integer areaId){
        return equipmentService.getEquipmentNameByStation(cityId,areaId);
    }

    /**
     *获取台站内的设备编号，用于工单的多选择框联动
     */
    @PostMapping("/equipment/getEquipmentNumberByStation")
    public String getEquipmentNameByStation(@RequestParam(value = "cityId" , required = true) Integer cityId,
                                            @RequestParam(value="areaId",required = true) Integer areaId,
                                            @RequestParam(value="name",required = true) String name){
        return equipmentService.getEquipmentNumberByStation(cityId,areaId,name);
    }

    @PostMapping("/equipment/getDataExpiredEquipment")
    public String getDataExpiredEquipment(@RequestParam(value = "cityId",required = false) Integer cityId,
                                          @RequestParam(value = "areaId",required = false) Integer areaId,
                                          @RequestParam(value = "type",required = false) Integer type){
        List<Map<String,Object>> dataExpiredEquipment = equipmentService.getDataExpiredEquipment(cityId,areaId,type);
        return JSONUtils.toJsonString(dataExpiredEquipment);
    }

    @PostMapping("/equipment/getOrdination")
    public String getOrdination(@RequestParam(value = "station",required = false) Integer cityId,
                                @RequestParam(value = "areaId",required = false) Integer areaId,
                                @RequestParam(value = "type",required = false) Integer type){
        List<Map<String,Object>> dataExpiredEquipment = equipmentService.getDataExpiredEquipment(cityId,areaId,type);
        return JSONUtils.toJsonString(dataExpiredEquipment);
    }

    @PostMapping("/equipment/addNewMarker")
    public CommonResult addNewMarker(
            @RequestParam(value = "regionId",required = false) Integer regionId,
            @RequestParam(value = "areaId",required = false) Integer areaId,
            @RequestParam(value = "MarkerType",required = false) Integer MarkerType,
            @RequestParam(value = "longitude",required = false) Float longitude,
            @RequestParam(value = "latitude",required = false) Float latitude){
        return equipmentService.addNewMarker(regionId,areaId,MarkerType,longitude,latitude);
    }

    @PostMapping("/equipment/getMarkerInfo")
    public String getMarkerInfo( @RequestParam(value = "regionId",required = false) Integer regionId,
                                 @RequestParam(value = "areaId",required = false) Integer areaId){
        return equipmentService.getMarkerInfo(regionId,areaId);
    }

    @PostMapping("/equipment/clearMarker")
    public CommonResult clearMarker(@RequestParam(value = "regionId",required = false) Integer regionId,
                                    @RequestParam(value = "areaId",required = false) Integer areaId){
        return equipmentService.clearMarker(regionId,areaId);
    }

    @PostMapping("/equipment/uploadIMG")
    public CommonResult uploadIMG(@RequestParam("img") MultipartFile image,
                                  @RequestParam("regionID")String region_id,
                                  @RequestParam("areaID")String area_id) throws IOException {
        int regionID = Integer.parseInt(region_id);
        int areaID =Integer.parseInt(area_id);
        return equipmentService.uploadPicture(image,regionID,areaID);
    }

    @GetMapping("/equipment/getIMG")
    public ResponseEntity<InputStreamResource> uploadIMG(@RequestParam("regionID")String region_id,
                                                         @RequestParam("areaID")String area_id) throws IOException {
        int regionID = Integer.parseInt(region_id);
        int areaID =Integer.parseInt(area_id);
        String picturePath=equipmentService.getPictureURL(regionID,areaID);
        File file=new File(picturePath);
        InputStreamResource resource=new InputStreamResource((new FileInputStream(file)));
        // 返回图片数据
        return ResponseEntity.ok()
                .contentType(MediaType.IMAGE_JPEG)
                .contentLength(file.length())
                .body(resource);
    }

//    /**
//     *
//     * @param cityId 城市id
//     * @param areaId 台站区域id
//     * @return plc和电源的近期数据情况（1是大于5分钟，0是小于5分钟）
//     */
//    @PostMapping("/equipment/getEquipmentRecentInfoTime")
//    public String getEquipmentRecentInfoTime(@RequestParam(value = "cityId",required = true) Integer cityId,
//                                             @RequestParam(value = "areaId",required = false) Integer areaId){
//        List<Map<String, Object>> powerRecentInfoTime = equipmentService.getPowerEquipmentRecentInfoTime(cityId, areaId);
//        List<Map<String, Object>> plcRecentInfoTime = equipmentService.getPlcEquipmentRecentInfoTime(cityId, areaId);
//        Map<String,Object> map = new HashMap<>();
//        map.put("plcRecentInfoTime",plcRecentInfoTime);
//        map.put("powerRecentInfoTime",powerRecentInfoTime);
//        return JSONUtils.toJsonString(map);
//    }
}
