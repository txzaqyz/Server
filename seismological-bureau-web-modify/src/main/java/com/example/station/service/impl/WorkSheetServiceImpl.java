package com.example.station.service.impl;

import com.alibaba.fastjson.JSON;
import com.example.station.entity.WorkSheet;
import com.example.station.mapper.EquipmentMapper;
import com.example.station.mapper.WorkSheetMapper;
import com.example.station.mapper.WorkSheetQuantityMapper;
import com.example.station.service.WorkSheetService;
import com.example.station.utils.CommonResult;
import com.example.station.utils.JSONUtils;
import com.example.station.websocket.WebsocketServer;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.*;

@Service
public class WorkSheetServiceImpl implements WorkSheetService {
    @Resource
    private WorkSheetMapper workSheetMapper;
    @Resource
    private EquipmentMapper equipmentMapper;
    @Resource
    private WorkSheetQuantityMapper workSheetQuantityMapper;

    /**
     * 用户创建工单
     * @param workSheet  工单实体类
     * @return
     */
    @Override
    @Transactional
    public CommonResult creatWorkSheetByUser(WorkSheet workSheet) {
        if (equipmentMapper.checkEquipmentNumber(workSheet.getNumber()) == 0) {
            return CommonResult.fail("设备不存在");
        }
        workSheet.setState(0);
        boolean success = workSheetMapper.creatWorkSheet(workSheet) != 0;
        equipmentMapper.updateEquipmentStateByNumber(workSheet.getNumber(), 1, new Timestamp(new Date().getTime()), workSheet.getInitiator());
        if (success) {
            WebsocketServer.BroadCastInfo("有新的工单提交");
            Calendar calendar = Calendar.getInstance();
            int month = calendar.get(Calendar.MONTH) + 1;
            int year = calendar.get(Calendar.YEAR);
            int cityId = workSheet.getRegionId();
            if(cityId != 0){
                if(year != workSheetQuantityMapper.getYearOfWorksheetQuantity(cityId,month)){
                    workSheetQuantityMapper.updateWorkSheetQuantity(cityId,month);
                    workSheetQuantityMapper.updateWorkSheetYear(year,cityId,month);
                }
                workSheetQuantityMapper.increaseWorkSheetQuantity(cityId,month);
            }
            return CommonResult.success("创建工单成功");
        } else {
            return CommonResult.success("创建工单失败");
        }
    }

    /**
     * 确认工单
     * @param id     工单id编号
     * @param staffId   员工id编号
     * @return
     */
    @Override
    public CommonResult confirmWorkSheet(int id, int staffId) {
        Timestamp timestamp = new Timestamp(new Date().getTime());
        boolean success = workSheetMapper.updateWorkSheet(id, staffId, timestamp, 1) != 0;
        return success ? CommonResult.success("确认工单成功") : CommonResult.fail("确认工单失败");
    }

    /**
     * 完成工单
     * @param id
     * @param staffId
     * @return
     */
    @Override
    public CommonResult finishWorkSheet(int id, int staffId) {
        WorkSheet workSheet = workSheetMapper.getOneWorkSheet(id);
        if (workSheet.getHandler() != staffId) {
            return CommonResult.fail("处理人不匹配");
        }
        boolean success = workSheetMapper.endWorkSheet(id, new Timestamp(new Date().getTime()), 2) != 0;
        return success ? CommonResult.success("结束工单成功") : CommonResult.fail("结束工单失败");
    }

    /**
     * 召回工单，只有创建人可以撤回，只有待确认的工单可以撤回
     * @param id
     * @param staffId
     * @return
     */
    @Transactional
    @Override
    public CommonResult recallWorkSheet(int id, int staffId) {
        WorkSheet workSheet = workSheetMapper.getOneWorkSheet(id);
        if (workSheet.getInitiator() == staffId && workSheet.getState() == 0) {
            int flag = workSheetMapper.deleteWorkSheet(id);
            equipmentMapper.updateEquipmentStateByNumber(workSheet.getNumber(), 0, new Timestamp(new Date().getTime()), staffId);
            if (flag != 0) {
                return CommonResult.success("删除工单成功");
            } else {
                return CommonResult.fail("删除工单失败");
            }
        } else {
            return CommonResult.fail("处理人不匹配");
        }
    }

    /**
     * 获取全部工单
     * @return
     */
    @Override
    public String getAllWorkSheet() {
        List<Map<String, Object>> unconfirmed = workSheetMapper.getAllUnconfirmedWorkSheet(0);
//        System.out.println(unconfirmed);
        List<Map<String, Object>> processing = workSheetMapper.getAllProcessingWorkSheet(1);
//        System.out.println(processing);
        return JSONUtils.toWorkSheetString("获取工单成功", unconfirmed, processing);
    }

    /**
     * 获取某地区工单
     * @param regionId
     * @return
     */
    @Override
    public String getWorkSheetByRegion(int regionId) {
        List<Map<String, Object>> unconfirmed = workSheetMapper.getUnconfirmedWorkSheetByRegion(0,regionId);
        List<Map<String, Object>> processing = workSheetMapper.getProcessingWorkSheetByRegion(1, regionId);
        return JSONUtils.toWorkSheetString("获取工单成功", unconfirmed, processing);
    }

    /**
     * 获取全部历史工单
     * @return
     */
    @Override
    public String getAllHistoryRecord() {
        List<Map<String, Object>> list = workSheetMapper.getHistoryRecord(2);
        return JSONUtils.toJSONString("获取工单成功", 200, list);
    }

    /**
     * 获取某地区历史工单
     * @param region
     * @return
     */
    @Override
    public String getHistoryRecordByRegion(int region) {
        List<Map<String, Object>> list = workSheetMapper.getHistoryRecordByRegion(2, region);
        return JSONUtils.toJSONString("获取工单成功", 200, list);
    }

    /**
     * 获取个人未完成和待处理的工单
     * @param staffId   员工id
     * @return
     */
    @Override
    public String getPersonalWorkSheet(int staffId) {
        List<Map<String, Object>> unconfirmed = workSheetMapper.getPersonalUnconfirmedWorkSheet(0, staffId);
        List<Map<String, Object>> processing = workSheetMapper.getPersonalProcessingWorkSheet(1, staffId);
        return JSONUtils.toWorkSheetString("获取工单成功", unconfirmed, processing);
    }

    /**
     * 获取城市当月的工单状态，包括本月已完成工单、本月处理中工单、本月待处理工单
     * @param regionId
     * @return
     */
    @Override
    public String getCityWorkSheetStatusMonth(int regionId) {
        Map<String,Object> map = new HashMap<>();
        Calendar calendar = Calendar.getInstance();
        int month = calendar.get(Calendar.MONTH) + 1;
        int year = calendar.get(Calendar.YEAR);
        Integer processing = workSheetMapper.getCityDiffTypeWorkSheetCount(regionId,1,null,null);
        Integer unconfirmed = workSheetMapper.getCityDiffTypeWorkSheetCount(regionId,0,null,null);
        Integer finished = workSheetMapper.getCityDiffTypeWorkSheetCount(regionId,2,month,year);
        map.put("processing",processing);
        map.put("unconfirmed",unconfirmed);
        map.put("finished",finished);
        return JSONUtils.toJsonString(map);
    }

    @Override
    public String getAllCityWorkSheetStatic() {
        List<Map<String, Object>> unprocessingList = workSheetMapper.getAllCityDiffTypeWorkSheetCount(0);
        List<Map<String, Object>> processingList = workSheetMapper.getAllCityDiffTypeWorkSheetCount(1);
        Map<String,List<Map<String, Object>>> map = new HashMap<>();
        map.put("unprocessing",unprocessingList);
        map.put("processing",processingList);
        return JSONUtils.toJsonString(map);
    }

    @Override
    public CommonResult deleteWorkSheetByNumber(String number) {
        workSheetMapper.deleteWorkSheetByNumber(number);
        return CommonResult.success("成功");
    }
}
