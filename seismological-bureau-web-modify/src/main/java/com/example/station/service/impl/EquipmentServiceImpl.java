package com.example.station.service.impl;

import com.example.station.entity.Equipment;
import com.example.station.entity.EquipmentRegister;
import com.example.station.mapper.EquipmentMapper;
import com.example.station.mapper.MenuAndStationMapper;
import com.example.station.mapper.WorkSheetMapper;
import com.example.station.service.EquipmentService;
import com.example.station.utils.CommonResult;
import com.example.station.utils.JSONUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.*;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;

@Service
public class EquipmentServiceImpl implements EquipmentService {
    @Resource
    private EquipmentMapper equipmentMapper;
    @Resource
    private MenuAndStationMapper menuAndStationMapper;
    @Resource
    private WorkSheetMapper workSheetMapper;

    /**
     * 添加设备
     * @param equipment
     * @return
     */
    @Override
    public CommonResult addEquipment(Equipment equipment) {
        if (equipmentMapper.checkEquipmentNumber(equipment.getNumber()) != 0) {
            return CommonResult.fail("设备编号已存在");
        } else {
            equipment.setMaintenance(0);
//            equipment.setEnable(0);
            equipment.setState(0);
            boolean success = equipmentMapper.addEquipment(equipment) != 0;
            return success ? CommonResult.success("添加设备成功") : CommonResult.fail("添加设备失败");
        }
    }

    @Override
    public CommonResult addEquipmentRegister(EquipmentRegister equipmentRegister) {
        if (equipmentMapper.checkEquipmentRegisterNumber(equipmentRegister.getNumber()) != 0) {
            return CommonResult.fail("设备编号已存在");
        } else {

            boolean success = equipmentMapper.addEquipmentRegister( equipmentRegister) != 0;
            return success ? CommonResult.success("注册设备成功") : CommonResult.fail("注册设备失败");
        }
    }
    /**
     * 更新设备状态，维修/正在使用
     *
     * @param id    设备id
     * @param state 设备状态0正常使用，1故障/维修，2移除
     * @return
     */
    @Override
    public CommonResult updateEquipmentState(int id, int state, int staffId) {
        Timestamp updateTime = new Timestamp(new Date().getTime());
        boolean success = equipmentMapper.updateEquipmentStateById(id, state, updateTime, staffId) != 0;
        return success ? CommonResult.success("更新设备状态成功") : CommonResult.fail("更新设备状态失败");
    }

    /**
     * 更新设备信息
     * @param equipment
     * @return
     */
    @Override
    public CommonResult updateEquipmentInformation(Equipment equipment) {
        boolean success = equipmentMapper.updateEquipmentInformation(equipment) != 0;
        return success ? CommonResult.success("更新设备状态成功") : CommonResult.fail("更新设备状态失败");
    }

    /**
     * 移除/报废设备（伪删除，不删库）
     *
     * @param id 设备id
     * @return
     */
    @Override
    public CommonResult deleteEquipment(int id, int staffId) {
        Timestamp updateTime = new Timestamp(new Date().getTime());
        int state = 2;
        boolean success = equipmentMapper.updateEquipmentStateById(id, state, updateTime, staffId) != 0;
        return success ? CommonResult.success("移除设备成功") : CommonResult.fail("移除设备失败");
    }

    /**
     * 按用户的地区编号获取设备清单，市级人员
     * @param regionId
     * @return
     */
    @Override
    public CommonResult getEquipments(int regionId) {
        List<Map<String, Object>> list = equipmentMapper.getEquipment(regionId);
        return createSuccessJsonResult(list);
    }

    /**
     * 获取全部设备清单，省级人员
     * @return
     */
    @Override
    public CommonResult getAllEquipments() {
        List<Map<String, Object>> list = equipmentMapper.getAllEquipment();
        return createSuccessJsonResult(list);
    }

    private CommonResult createSuccessJsonResult(List<Map<String, Object>> list) {
        return CommonResult.success("设备获取成功", list);
    }

    /**
     * 按照cityId获得不同状态设备数量
     * @param cityId
     * @return
     */
    @Override
    public String getCityEquipmentStatus(int cityId) {
        Map<String,Object> map = new HashMap<>();
        int workingEquipmentCount = equipmentMapper.getEquipmentStatus(cityId, 0);
        int abnormalEquipmentCount = equipmentMapper.getEquipmentStatus(cityId, 1);
        map.put("workingEquipmentCount",workingEquipmentCount);
        map.put("abnormalEquipmentCount",abnormalEquipmentCount);
        return JSONUtils.toJsonString(map);
    }

    @Override
    public String getEquipmentNumStatic() {
        List<Map<Integer, Integer>> list = equipmentMapper.getAllCityEquipmentNum();
        return JSONUtils.toJsonString(list);
    }

    @Override
    public String getEquipmentInNumYear() {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        List<Map<Integer, Integer>> list = equipmentMapper.getAllCityEquipmentYear(year);
        return JSONUtils.toJsonString(list);
    }

    @Override
    public String getEquipmentCountThroughStatus() {
        List<Map<Integer, Integer>> list = equipmentMapper.getEquipmentCountByStatus();
        return JSONUtils.toJsonString(list);
    }

    /**
     *
     * @param cityId
     * @return 应当返回areaId+当前区域状态
     */
    @Override
    public Map<String,Object> getPatrolStatus(Integer cityId){
        List<Equipment> patrolStatus = equipmentMapper.getPatrolStatus(cityId);
//        System.out.println(patrolStatus);
        Map<String,Boolean> areaMap = new HashMap<>();
        for(Equipment patrolDevice : patrolStatus){
            String areaId = String.valueOf(patrolDevice.getAreaId());
            Boolean connection = patrolDevice.getConnectionStatus();
            if(areaMap.containsKey(areaId)){
                if(areaMap.get(areaId) != TRUE){
                    continue;
                }else if(!connection){
                    areaMap.replace(areaId,FALSE);
                }
            }else{
                areaMap.put(areaId,connection);
            }
        }
        Map<String,Object> cityMap = new HashMap<>();
        if(!areaMap.isEmpty()) cityMap.put(cityId.toString(),areaMap);
        return cityMap;
    }

    @Override
    public String getPatrolStatus(Integer cityId,Integer areaId){
        List<Equipment> areaPatrolStatus = equipmentMapper.getAreaPatrolStatus(cityId, areaId);
        return JSONUtils.toJsonString(areaPatrolStatus);
    }

    @Override
    public List<Map<String,Object>> getPatrolStatus() {
        int count = menuAndStationMapper.getRegionNum();
        List<Map<String,Object>> list = new LinkedList<>();
        for(int i = 1; i <= count; i++){
            Map<String,Object> cityPatrolMap = new HashMap<>();
            cityPatrolMap.put("cityId",Integer.valueOf(i));//这里需要再斟酌，如果城市id和顺序不同怎么做？需要真正获得数据库存储的cityId
            boolean isConnection = true;
            Map<String, Object> map = getPatrolStatus(i);
            for(Object cityConnectionStatus : map.values()){
                Map<String,Boolean> cityStatusMap = (Map<String,Boolean>)cityConnectionStatus;
                for(Boolean areaConnectionStatus : cityStatusMap.values()){
                    isConnection &= areaConnectionStatus;
                }
            }
            cityPatrolMap.put("isConnection",isConnection);
            list.add(cityPatrolMap);
        }
        return list;
    }

    @Override
    public CommonResult fullDeleteEquipment(String number) {
        workSheetMapper.deleteWorkSheetByNumber(number);
        equipmentMapper.fullDeleteEquipment(number);
        return CommonResult.success("删除成功");
    }

    @Override
    public String getEquipmentNameByStation(int cityId, int areaId) {
        return JSONUtils.toJsonString(equipmentMapper.getEquipmentNameByStation(cityId,areaId));
    }

    @Override
    public String getEquipmentNumberByStation(int cityId, int areaId, String name) {
        return JSONUtils.toJsonString(equipmentMapper.getEquipmentNumberByStation(cityId,areaId,name));
    }

    @Override
    public String getAllEquipmentInfo(int cityId, int areaId, int type) {
        if(type != 1){
            List<String> EquipList = equipmentMapper.getAreaPatrolDeviceByType(cityId, areaId, type);
            return JSONUtils.toJsonString(EquipList);
        }else{
            List<Map<String,Object>> EquipList = equipmentMapper.getAreaPatrolDeviceOfCamera(cityId,areaId);
            return JSONUtils.toJsonString(EquipList);
        }

    }

    @Override
    public List<Map<String, Object>> getDataExpiredEquipment(Integer cityId, Integer areaId, Integer type) {
        return equipmentMapper.getDataExpiredEquipment(cityId,areaId,type);
    }


    @Override
    public CommonResult addNewMarker(Integer regionId,Integer areaId,Integer MarkerType,Float longitude,Float latitude) {
        if(MarkerType==0){
            boolean success = equipmentMapper.addCameraMarker(regionId,areaId,longitude,latitude) != 0;
            return success ? CommonResult.success("添加摄像头标记成功"):CommonResult.fail("添加标记失败");
        }else{
            boolean success = equipmentMapper.addPowerMarker(regionId,areaId,longitude,latitude) != 0;
            return success ? CommonResult.success("添加电源标记成功"):CommonResult.fail("添加失败");
        }
    }

    @Override
    public String getMarkerInfo(Integer regionId,Integer areaId){
        return JSONUtils.toJsonString(equipmentMapper.getMarkerInfo(regionId,areaId));
    }

    @Override
    public CommonResult clearMarker(Integer regionId,Integer areaId){
        if(equipmentMapper.clearMarker(regionId,areaId)!=0) {
            return  CommonResult.success("成功删除全部标记");
        }else {
            return CommonResult.fail("删除失败");
        }
    }
    @Override
    public  CommonResult uploadPicture(MultipartFile image, Integer region_id, Integer area_id){
        String basePath="D:\\Desktop\\qianhou\\StaionPicture\\";
        System.out.println("file======="+image);
        String originalFilename = image.getOriginalFilename();
        System.out.println("originalFilename====="+originalFilename);
        String suffix = originalFilename.substring(originalFilename.lastIndexOf("."));
        String fileName = UUID.randomUUID().toString() + suffix;//
        //创建一个目录对象
        File dir = new File(basePath);
        //判断当前目录是否存在
        String lujin=basePath+fileName;
        String deletePath=getPictureURL(region_id,area_id);//得到之前存储的图片
        if(!dir.exists()){
            //目录不存在，需要创建
            dir.mkdirs();
        }  //将临时文件转存到指定位置
        try {
            image.transferTo(new File(basePath + fileName));
            int temp=equipmentMapper.uploadPicture(lujin,region_id,area_id);
            if(temp!=0){
                deleteIMG(deletePath);
                return CommonResult.success("成功添加台站图片");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return CommonResult.fail("添加图片失败");
    }

    @Override
    public String getPictureURL(Integer reginID,Integer areaID){
        String PictureURL=equipmentMapper.getPictureURL(reginID,areaID);
        if(PictureURL!=null) {
            return equipmentMapper.getPictureURL(reginID, areaID);
        }else{
            return "D:\\Desktop\\Sise\\StaionPicture\\stationDefault.jpg";
        }
    }

    @Override
    public void deleteIMG(String imagePath){
        try {
            File file=new File(imagePath);
            if(file.exists()) {
                file.delete();
            }
            System.out.println("图片删除成功：" + imagePath);
        } catch (Error e) {
            System.out.println("图片删除失败：" + imagePath);
            e.printStackTrace();
        }
    }


    //    @Override
//    public List<Map<String,Object>> getPowerEquipmentRecentInfoTime(Integer cityId, Integer areaId) {
//        return equipmentMapper.getPowerEquipmentRecentInfoTime(cityId, areaId);
//    }
//
//    @Override
//    public List<Map<String,Object>> getPlcEquipmentRecentInfoTime(Integer cityId, Integer areaId) {
//        return equipmentMapper.getPlcEquipmentRecentInfoTime(cityId, areaId);
//    }
}
