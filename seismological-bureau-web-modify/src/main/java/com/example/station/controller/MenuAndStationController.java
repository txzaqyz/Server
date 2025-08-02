package com.example.station.controller;

import com.example.station.entity.Menu;
import com.example.station.menu.AreaLevelConst;
import com.example.station.service.impl.MenuAndStationServiceImpl;
import com.example.station.utils.CommonResult;
import com.example.station.utils.JWTUtils;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.List;

@RestController
public class MenuAndStationController {
    @Resource
    private MenuAndStationServiceImpl menuAndStationService;

    /**
     * 获取用户菜单栏，按权限等级生成
     * @param request
     * @return
     */
    @PostMapping("/menuAndStation/getMenu")
    public CommonResult<List<Menu>> getMenu(HttpServletRequest request){
        int authority = JWTUtils.verifyAuthority(JWTUtils.parseToken(request));
        return menuAndStationService.getMenu(authority);
    }

    /**
     * 获取全部地区列表
     * @return
     */
    @PostMapping("/menuAndStation/getArea")
    public String getArea(){
        return menuAndStationService.getArea();
    }

    /**
     * 获取全部台站列表
     * @return
     */
    @PostMapping("/menuAndStation/getStation")
    public String getStation(){
        return menuAndStationService.getStation();
    }

    /**
     * 按地区获取台站列表
     * @param request
     * @return
     */
    @PostMapping("/menuAndStation/getStationByRegion")
    public String getStationByRegion(HttpServletRequest request,@RequestParam(value = "regionId", required = true) int regionId){
//        int regionId = JWTUtils.parseRegionId(JWTUtils.parseToken(request));
        return menuAndStationService.getStationByRegion(regionId);
    }

    /**
     * 新增台站，省级管理员
     * @param request
     * @param address 台站名
     * @param level   台站级别，0省级，1市级,尚未实装
     * @param regionId  台站所属地区id
     * @return
     */
    @PostMapping("/menuAndStation/addStation")
    public CommonResult addStation(HttpServletRequest request,
                                   @RequestParam(value = "address", required = true) String address,
                                   @RequestParam(value = "areaLevel", required = true) int level,
                                   @RequestParam(value = "regionId", required = true) int regionId,
                                   @RequestParam(value = "coordinateX", required = true) double coordinateX,
                                   @RequestParam(value = "coordinateY", required = true) double coordinateY) {
        String token = JWTUtils.parseToken(request);
        int authority = JWTUtils.verifyAuthority(token);
        int areaLevel = JWTUtils.parseAreaLevel(token);
        if (authority == 0 && areaLevel == AreaLevelConst.PROVINCE) {
            return menuAndStationService.addStation(address, level, regionId,coordinateX,coordinateY);
        } else {
            return CommonResult.noPermission();
        }
    }

    /**
     * 删除台站
     */
    @PostMapping("/menuAndStation/deleteStation")
    public CommonResult deleteStation(HttpServletRequest request,
                                      @RequestParam(value = "regionId", required = true) int regionId,
                                      @RequestParam(value = "areaId", required = true) int areaId){
        String token = JWTUtils.parseToken(request);
        int authority = JWTUtils.verifyAuthority(token);
        int areaLevel = JWTUtils.parseAreaLevel(token);
        if (authority == 0 && areaLevel == AreaLevelConst.PROVINCE) {
            return menuAndStationService.deleteStation(regionId,areaId);
        } else {
            return CommonResult.noPermission();
        }
    }
    /**
     * 得到市的图片
     */
    @PostMapping("/menuAndStation/getPictureUrl")
    public ResponseEntity<InputStreamResource> getCityPicture(HttpServletRequest request,
                                                              @RequestParam(value = "regionId", required = true) int regionId) throws FileNotFoundException {
        String picturePath=menuAndStationService.getCityPicture(regionId);
        File file=new File(picturePath);
        InputStreamResource resource=new InputStreamResource((new FileInputStream(file)));
        // 返回图片数据
        return ResponseEntity.ok()
                .contentType(MediaType.IMAGE_JPEG)
                .contentLength(file.length())
                .body(resource);


    }
}
