package com.example.station.controller;

import com.example.station.entity.Supplier;
import com.example.station.entity.User;
import com.example.station.service.impl.SupAndManuServiceImpl;
import com.example.station.utils.CommonResult;
import com.example.station.utils.JWTUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/SupAndManu")
public class SupAndManuController {

    @Resource
    SupAndManuServiceImpl supAndManuService;


    @RequestMapping("/addNewSupplier")
    public  CommonResult addNewSupplier(@RequestBody Supplier supplier,HttpServletRequest request){
        int authority = JWTUtils.verifyAuthority(JWTUtils.parseToken(request));
        if(authority!=0)return  CommonResult.fail("权限错误，操作失败");
        return supAndManuService.addNewSupplier(supplier);
    }

    @RequestMapping("/deleteSupplier")
    public  CommonResult deleteSupplier(HttpServletRequest request){
        int authority = JWTUtils.verifyAuthority(JWTUtils.parseToken(request));
        if(authority!=0)return  CommonResult.fail("权限错误，操作失败");
        String code=request.getParameter("code");
        return supAndManuService.deleteSupplier(code);
    }

    @RequestMapping("/modifySupplier")
    public  CommonResult modifySupplier(HttpServletRequest request ,@RequestBody  Supplier supplier){
        int authority = JWTUtils.verifyAuthority(JWTUtils.parseToken(request));
        if(authority!=0)return  CommonResult.fail("权限错误，操作失败");
        return supAndManuService.modifySupplier(supplier);
    }

    @RequestMapping("/getAllSupplier")
    public  CommonResult getAllSupplier(){
        return supAndManuService.getAllSupplier();
    }

    @RequestMapping("/addNewManu")
    public  CommonResult addNewManu(@RequestBody Supplier supplier,HttpServletRequest request){
        int authority = JWTUtils.verifyAuthority(JWTUtils.parseToken(request));
        if(authority!=0)return  CommonResult.fail("权限错误，操作失败");
        return supAndManuService.addNewManu(supplier);
    }

    @RequestMapping("/deleteManu")
    public  CommonResult deleteManu(HttpServletRequest request){
        int authority = JWTUtils.verifyAuthority(JWTUtils.parseToken(request));
        if(authority!=0)return  CommonResult.fail("权限错误，操作失败");
        String code=request.getParameter("code");
        return supAndManuService.deleteManu(code);
    }

    @RequestMapping("/modifyManu")
    public  CommonResult modifyManu(HttpServletRequest request ,@RequestBody  Supplier supplier){
        int authority = JWTUtils.verifyAuthority(JWTUtils.parseToken(request));
        if(authority!=0)return  CommonResult.fail("权限错误，操作失败");
        return supAndManuService.modifyManu(supplier);
    }

    @RequestMapping("/getAllManu")
    public  CommonResult getAllManu(){
        return supAndManuService.getAllManu();
    }


}
