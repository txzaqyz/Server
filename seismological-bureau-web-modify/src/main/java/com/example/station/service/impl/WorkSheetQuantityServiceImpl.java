package com.example.station.service.impl;

import com.example.station.entity.WorkSheetNum;
import com.example.station.mapper.WorkSheetQuantityMapper;
import com.example.station.service.WorkSheetQuantityService;
import com.example.station.utils.JSONUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;

@Service
public class WorkSheetQuantityServiceImpl implements WorkSheetQuantityService {
    @Resource
    private WorkSheetQuantityMapper workSheetQuantityMapper;

    @Override

    public String getCityWorkSheetYear(int cityId) {
        List<WorkSheetNum> workSheetNumList = workSheetQuantityMapper.getCityWorkSheetAllMonth(cityId);
        List<Integer> orderNum = new LinkedList<>();
        Calendar calendar = Calendar.getInstance();
        int yearNow = calendar.get(Calendar.YEAR);
        for(WorkSheetNum workSheetNum:workSheetNumList){
            if(workSheetNum.getYear() != yearNow){
                orderNum.add(0);
            }else{
                orderNum.add(workSheetNum.getWorksheet_quantity());
            }
        }
        return JSONUtils.toJsonString(orderNum);
    }
}
