package com.example.station.service.impl;

import com.alibaba.fastjson.JSON;
import com.example.station.entity.PowerYuanHang;
import com.example.station.entity.YuanHangWebSimpleData;
import com.example.station.mapper.PowerYuanHangMapper;
import com.example.station.service.PowerYuanHangService;
import com.example.station.utils.JSONUtils;
import com.example.station.utils.PowerYuanHangToWebHandleUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class PowerYuanHangServiceImpl implements PowerYuanHangService {
    @Resource
    private PowerYuanHangMapper powerYuanHangMapper;

    @Override
    public String firstGetSimpleData(String powerId) {
        List<YuanHangWebSimpleData> answer = new ArrayList<>();
        List<PowerYuanHang> list = powerYuanHangMapper.firstGetSimpleData(powerId);
//        System.out.println("list:"+list);
        for(int i = 0; i < list.size(); i++){
            PowerYuanHang power = list.get(list.size() - i - 1);
            YuanHangWebSimpleData data = new YuanHangWebSimpleData();
            PowerYuanHangToWebHandleUtils.creatData(data, power);
            answer.add(data);
        }
        return JSONUtils.toJsonString(answer);
    }

    @Override
    public String getSimpleData(String powerId) {
        YuanHangWebSimpleData data = new YuanHangWebSimpleData();
        PowerYuanHang power = powerYuanHangMapper.getSimpleData(powerId);
        PowerYuanHangToWebHandleUtils.creatData(data, power);
        return JSON.toJSONString(data);
    }

}
