package com.example.station.controller;

import com.cims.sync.constant.JsonResult;
import com.cims.sync.dto.ApiPushDTO;
import com.cims.sync.exception.ErrorCode;
import com.cims.sync.util.MD5Util;
import com.cims.sync.util.SecurityUtil;
import com.cims.sync.util.SignBuilder;
import com.example.station.config.SSOClientConfig;
import com.example.station.service.impl.UserServiceImpl;
import com.example.station.sso.XindunAccessGrant;
import com.example.station.sso.XindunRquestTemplate;
import com.example.station.utils.CommonResult;
import com.example.station.utils.JsonUtil;
import com.example.station.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

//import com.trusfort.cims.demo.dao.AccountInfoDao;
//import com.trusfort.cims.demo.dao.DeptInfoDao;
//import lombok.extern.log4j.Log4j2;
//import org.apache.commons.lang3.StringUtils;

/**
 * @Classname UserController
 * @Description None
 * @Date 2019/6/25 17:51
 * @author wd
 */
@RestController
@RequestMapping("/sso")
public class SSOController {
    @Resource
    private UserServiceImpl userService;

    @Autowired
    private SSOClientConfig ssoClientConfig;

//    @Autowired
//    private DeptInfoDao deptInfoDao;
//
//    @Autowired
//    private AccountInfoDao accountInfoDao;

    @GetMapping
    public Object sso(String code) {
        System.out.println(code);
        XindunRquestTemplate rquestTemplate=new XindunRquestTemplate(ssoClientConfig);
        XindunAccessGrant xindunAccessGrant=rquestTemplate.exchangeForAccess(code);
        String userName=rquestTemplate.getAccount(xindunAccessGrant);
        System.out.println(userName);

        if(code!=null) {

            return userService.loginSSO(userName);
        }

        return null;
    }
}
