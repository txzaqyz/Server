package com.example.station.service;

import com.example.station.entity.Supplier;
import com.example.station.utils.CommonResult;

import java.util.List;

public interface SupAndManuService {
    CommonResult addNewSupplier(Supplier supplier);

    CommonResult deleteSupplier(String code);

    CommonResult modifySupplier(Supplier supplier);

    CommonResult<List<Supplier>> getAllSupplier();

    CommonResult addNewManu(Supplier supplier);

    CommonResult deleteManu(String code);

    CommonResult modifyManu(Supplier supplier);

    CommonResult<List<Supplier>> getAllManu();
}
