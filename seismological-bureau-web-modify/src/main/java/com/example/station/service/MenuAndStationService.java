package com.example.station.service;

import com.example.station.entity.Menu;
import com.example.station.utils.CommonResult;

import java.util.List;

public interface MenuAndStationService {
    CommonResult<List<Menu>> getMenu(int authority);

    String getArea();

    String getStation();

    String getStationByRegion(int regionId);

    CommonResult addStation(String address, int level, int regionId,double coordinate_x,double coordinate_y);

    CommonResult deleteStation(int regionId,int areaId);

    String getCityPicture(int regionId);
}
