package com.example.station.service.impl;

import com.example.station.entity.Supplier;
import com.example.station.mapper.SupAndManuMapper;
import com.example.station.service.SupAndManuService;
import com.example.station.utils.CommonResult;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class SupAndManuServiceImpl  implements SupAndManuService {

    @Resource
    SupAndManuMapper mapper;

    @Override
    public CommonResult addNewSupplier(Supplier supplier) {
        int num=mapper.FindCode(supplier.getCode());
        if(num!=0)return CommonResult.fail("供应商编号需要唯一");
        int success =mapper.addNewSupplier(supplier);
        System.out.println(success);
        return success!=0?CommonResult.success("添加成功"):CommonResult.fail("添加失败");
    }

    @Override
    public CommonResult deleteSupplier(String code) {
        int success=mapper.deleteSupplier(code);
        return success!=0?CommonResult.success("删除成功"):CommonResult.fail("删除失败");
    }

    @Override
    public CommonResult modifySupplier(Supplier supplier) {
        int success=mapper.updateSupplier(supplier);
        System.out.println(success);
        return success!=0?CommonResult.success("修改成功"):CommonResult.fail("修改失败");
    }

    @Override
    public CommonResult<List<Supplier>> getAllSupplier() {
        List<Supplier> list=mapper.getAllSupplier();
        return CommonResult.success("获取成功",list);
    }

    @Override
    public CommonResult addNewManu(Supplier supplier) {
        int num=mapper.FindCode2(supplier.getCode());
        if(num!=0)return CommonResult.fail("制造商编号需要唯一");
        int success =mapper.addNewManu(supplier);
        System.out.println(success);
        return success!=0?CommonResult.success("添加成功"):CommonResult.fail("添加失败");
    }

    @Override
    public CommonResult deleteManu(String code) {
        int success=mapper.deleteManu(code);
        return success!=0?CommonResult.success("删除成功"):CommonResult.fail("删除失败");
    }

    @Override
    public CommonResult modifyManu(Supplier supplier) {
        int success=mapper.updateManu(supplier);
        System.out.println(success);
        return success!=0?CommonResult.success("修改成功"):CommonResult.fail("修改失败");
    }

    @Override
    public CommonResult<List<Supplier>> getAllManu() {
        List<Supplier> list=mapper.getAllManu();
        return CommonResult.success("获取成功",list);
    }
}
