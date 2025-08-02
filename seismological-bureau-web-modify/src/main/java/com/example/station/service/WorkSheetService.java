package com.example.station.service;

import com.example.station.entity.WorkSheet;
import com.example.station.utils.CommonResult;

public interface WorkSheetService {
    CommonResult creatWorkSheetByUser(WorkSheet workSheet);

    CommonResult confirmWorkSheet(int id, int staffId);

    CommonResult finishWorkSheet(int id, int staffId);

    CommonResult recallWorkSheet(int id, int staffId);

    String getAllWorkSheet();

    String getWorkSheetByRegion(int regionId);

    String getAllHistoryRecord();

    String getHistoryRecordByRegion(int region);

    String getPersonalWorkSheet(int staffId);

    String getCityWorkSheetStatusMonth(int regionId);

    String getAllCityWorkSheetStatic();

    CommonResult deleteWorkSheetByNumber(String number);
}
