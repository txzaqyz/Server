package com.example.station.controller;

import com.example.station.service.impl.PowerYuanHangServiceImpl;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class PowerYuanHangController {
    @Resource
    private PowerYuanHangServiceImpl powerYuanHangService;

    /**
     * 获取智能电源的最新一次参数
     * @param powerId  智能电源台站编号
     * @return
     */
    @PostMapping("/yuanHang/getSimpleData")
    public String getSimpleData(@RequestParam(value = "powerId", required = true) String powerId){
        return powerYuanHangService.getSimpleData(powerId);
    }

    /**
     * 第一次获取电源参数
     * @param powerId   智能电源台站编号
     * @return
     */
    @PostMapping("/yuanHang/firstGetSimpleData")
    public String firstGetSimpleData(@RequestParam(value = "powerId", required = true) String powerId){
        return powerYuanHangService.firstGetSimpleData(powerId);
    }

}
