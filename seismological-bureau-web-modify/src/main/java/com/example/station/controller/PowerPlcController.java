package com.example.station.controller;

import com.example.station.service.impl.PlcServiceImpl;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class PowerPlcController {
    @Resource
    private  PlcServiceImpl plcService;

    /**
     * 测试获取PLC数据
     * @param type 是哪种类型的数据
     * @param mac 是个mac地址的plc数据
     * @return
     */

    @PostMapping("/plc/getPlcData")
    public String getPlcData(@RequestParam(value = "type", required = true) int type,
                             @RequestParam(value = "mac", required = true) String mac){
        return plcService.getPlcData(type,mac);
    }
}
