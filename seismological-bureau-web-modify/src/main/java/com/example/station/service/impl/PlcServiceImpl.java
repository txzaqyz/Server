package com.example.station.service.impl;

import com.example.station.entity.PowerPlc;
import com.example.station.mapper.PlcMapper;
import com.example.station.service.PlcService;
import com.example.station.utils.JSONUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class PlcServiceImpl implements PlcService {
    @Resource
    private PlcMapper plcMapper;

    @Override
    public String getPlcData(int type, String mac) {
        PowerPlc res = plcMapper.getPlcDataOneTime(type,mac);
        return JSONUtils.toJsonString(res);

    }
}
