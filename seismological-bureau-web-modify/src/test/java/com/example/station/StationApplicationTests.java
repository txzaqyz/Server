package com.example.station;


import com.alibaba.fastjson.JSON;
import com.example.station.entity.Supplier;
import com.example.station.mapper.SupAndManuMapper;
import com.example.station.service.impl.SupAndManuServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.List;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class StationApplicationTests {
    @Resource
    private SupAndManuServiceImpl service;
    private SupAndManuMapper map;
    @Test
    public void  test(){

//        System.out.println(service.getAllSupplier().getData());
    }
}
