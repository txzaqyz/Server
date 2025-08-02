package com.example.station.controller;

import com.example.station.service.WorkSheetQuantityService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@RestController
public class WorkSheetQuantityController {
    @Resource
    private WorkSheetQuantityService workSheetQuantityService;

    /**
     * 功能：获取某一城市本年度各月份工单数量
     * @param cityId 城市id
     * @return
     */
    @PostMapping("/WorkSheetQuantity/getCityWorkSheetAllMonth")
    public String getCityWorkSheetAllMonth(int cityId){
        return workSheetQuantityService.getCityWorkSheetYear(cityId);
    }

}
