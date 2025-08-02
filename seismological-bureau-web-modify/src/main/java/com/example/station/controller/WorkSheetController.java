package com.example.station.controller;

import com.example.station.annotation.CheckOperationAuthority;
import com.example.station.entity.WorkSheet;
import com.example.station.menu.AreaLevelConst;
import com.example.station.service.impl.WorkSheetServiceImpl;
import com.example.station.utils.CommonResult;
import com.example.station.utils.JWTUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@RestController
public class WorkSheetController {

    @Resource
    private WorkSheetServiceImpl workSheetService;

    /**
     * 创建工单
     * @param request
     * @param workSheet  工单实体类
     * @return
     */
    @CheckOperationAuthority
    @PostMapping("/workSheet/creatWorkSheet")
    public CommonResult creatWorkSheet(HttpServletRequest request,
                                       @RequestBody WorkSheet workSheet){
        String token = JWTUtils.parseToken(request);
        int staffId = JWTUtils.parseId(token);
        workSheet.setInitiator(staffId);
        return workSheetService.creatWorkSheetByUser(workSheet);
    }

    /**
     * 删除工单
     */
    @CheckOperationAuthority
    @PostMapping("/workSheet/deleteWorkSheet")
    public CommonResult deleteWorkSheet(HttpServletRequest request,
                                       @RequestParam(value = "number", required = true) String number){
        String token = JWTUtils.parseToken(request);
        int staffId = JWTUtils.parseId(token);
        return workSheetService.deleteWorkSheetByNumber(number);
    }


    /**
     * 确认工单
     * @param request
     * @param id    工单编号
     * @return
     */
    @CheckOperationAuthority
    @PostMapping("/workSheet/confirmWorkSheet")
    public CommonResult confirmWorkSheet(HttpServletRequest request,
                                   @RequestParam(value = "id", required = true) int id) {
        int staffId = JWTUtils.parseId(JWTUtils.parseToken(request));
        return workSheetService.confirmWorkSheet(id, staffId);
    }

    /**
     * 完成工单
     * @param request
     * @param id     工单编号
     * @return
     */
    @CheckOperationAuthority
    @PostMapping("/workSheet/finishWorkSheet")
    public CommonResult finishWorkSheet(HttpServletRequest request,
                                   @RequestParam(value = "id", required = true) int id) {
        int staffId = JWTUtils.parseId(JWTUtils.parseToken(request));
        return workSheetService.finishWorkSheet(id, staffId);

    }

    /**
     * 撤回工单
     * @param request
     * @param id   工单编号
     * @return
     */
    @CheckOperationAuthority
    @PostMapping("/workSheet/recallWorkSheet")
    public CommonResult recallWorkSheet(HttpServletRequest request,
                                  @RequestParam(value = "id", required = true) int id){
        int staffId = JWTUtils.parseId(JWTUtils.parseToken(request));
        return workSheetService.recallWorkSheet(id, staffId);
    }

    /**
     * 查询工单，待确认和处理中的
     * @param request
     * @return
     */
    @CheckOperationAuthority
    @PostMapping("/workSheet/getWorkSheet")
    public String getWorkSheet(HttpServletRequest request){
        String token = JWTUtils.parseToken(request);
        int regionId = JWTUtils.parseRegionId(token);
        int areaLevel = JWTUtils.parseAreaLevel(token);

        if (areaLevel == AreaLevelConst.PROVINCE) {
            return workSheetService.getAllWorkSheet();
        } else {
            return workSheetService.getWorkSheetByRegion(regionId);
        }

    }

    /**
     * 查询历史工单，已完成的
     * @param request
     * @return
     */
    @CheckOperationAuthority
    @PostMapping("/workSheet/getHistoryRecord")
    public String getHistoryRecord(HttpServletRequest request){
        String token = JWTUtils.parseToken(request);
        int regionId = JWTUtils.parseRegionId(token);
        int areaLevel = JWTUtils.parseAreaLevel(token);
        if (areaLevel == AreaLevelConst.PROVINCE) {
            return workSheetService.getAllHistoryRecord();
        } else {
            return workSheetService.getHistoryRecordByRegion(regionId);
        }
    }

    /**
     * 获取个人工单
     * @param request
     * @return
     */
    @CheckOperationAuthority
    @PostMapping("/workSheet/getPersonalWorkSheet")
    public String getPersonalWorkSheet(HttpServletRequest request){
        int staffId = JWTUtils.parseId(JWTUtils.parseToken(request));
        return workSheetService.getPersonalWorkSheet(staffId);
    }

    /**
     * 获取月工单状态
     * @param request
     * @return
     */
    @CheckOperationAuthority
    @PostMapping("/workSheet/getCityWorkSheetStatusMonth")
    public String getCityWorkSheetStatusMonth(HttpServletRequest request, Integer cityId){
//        int staffId = JWTUtils.parseId(JWTUtils.parseToken(request));
        return workSheetService.getCityWorkSheetStatusMonth(cityId);
    }

    @CheckOperationAuthority
    @PostMapping("/workSheet/getAllCityWorkSheetStatic")
    public String getAllCityWorkSheetStatic(HttpServletRequest request){
        return workSheetService.getAllCityWorkSheetStatic();
    }
}
