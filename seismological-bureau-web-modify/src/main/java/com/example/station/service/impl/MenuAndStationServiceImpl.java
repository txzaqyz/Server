package com.example.station.service.impl;

import com.alibaba.fastjson.JSON;
import com.example.station.entity.Area;
import com.example.station.entity.Menu;
import com.example.station.entity.Station;
import com.example.station.mapper.EquipmentMapper;
import com.example.station.mapper.MenuAndStationMapper;
import com.example.station.service.MenuAndStationService;
import com.example.station.utils.CommonResult;
import com.example.station.utils.MenuUtils;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class MenuAndStationServiceImpl implements MenuAndStationService {
    @Resource
    private MenuAndStationMapper menuAndStationMapper;
    @Resource
    private EquipmentMapper equipmentMapper;

    /**
     * 获取台站菜单
     * @param authority  用户权限级别
     * @return
     */
    @Override
    @Cacheable(value = "menu")
    public CommonResult<List<Menu>> getMenu(int authority){
        List<Menu> list;
        CommonResult<List<Menu>> res = null;
        switch (authority) {
            case 0:
            case 1:
                list = MenuUtils.creatMenu1();
                res = CommonResult.success("获取成功",list);
                return res;
            case 2:
                list = MenuUtils.creatMenu2();
                return CommonResult.success("获取成功",list);
            default:
                res = CommonResult.noPermission();
        }
        return res;
    }

    @Override
    public String getArea() {
        List<Area> list= menuAndStationMapper.getArea();
        return JSON.toJSONString(list);
    }

    @Override
    public String getStation() {
        List<Station> list= menuAndStationMapper.getStation();
        return JSON.toJSONString(list);
    }

    @Override
    public String getStationByRegion(int regionId) {
        List<Station> list= menuAndStationMapper.getStationByRegion(regionId);
        return JSON.toJSONString(list);
    }

    @Override
    public CommonResult addStation(String address, int level, int regionId,double coordinate_x,double coordinate_y) {
        if (menuAndStationMapper.checkStation(address,regionId) != 0) {
            return CommonResult.fail("台站名已存在");
        } else {
            boolean success = menuAndStationMapper.addStation(address, level, regionId,coordinate_x,coordinate_y) != 0;
            return success ? CommonResult.success("添加台站成功"):CommonResult.fail("添加台站失败");
        }
    }

    @Override
    public CommonResult deleteStation(int regionId, int areaId) {
        if(equipmentMapper.getCityEquipmentNum(regionId,areaId) > 0){
            return CommonResult.fail("需先删除台站设备");
        }else if(menuAndStationMapper.checkStationCenter(regionId,areaId)){
            if(menuAndStationMapper.checkStationCount(regionId)>1);
            return CommonResult.fail("中心台站需最后删除");
        }else{
            boolean success = menuAndStationMapper.deleteStation(regionId,areaId)!= 0;
            return success ? CommonResult.success("删除台站成功"):CommonResult.fail("删除台站失败");
        }
    }

    @Override
    public String getCityPicture(int regionId) {
        String url=menuAndStationMapper.getCityPicture(regionId);
        return url;
    }
}
