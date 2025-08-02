package com.example.station.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SimplePropertyPreFilter;
import com.example.station.entity.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 生成JSON的工具类
 */
public class JSONUtils {
    private static final String KEY_MSG = "msg";
    private static final String KEY_STATUS = "status";
    private static final String KEY_DATA = "data";

    public static String toJSONString(String message, int status){
        HashMap<String, Object> map = new HashMap<>();
        map.put(KEY_MSG, message);
        map.put(KEY_STATUS, status);
        return JSON.toJSONString(map);
    }

    public static<T> String toJsonString(List<T> list){
        HashMap<String,Object> map = new HashMap<>();
        map.put(KEY_DATA,list);
        return JSON.toJSONString(map);
    }

    public static<K,V> String toJsonString(Map<K,V> map){
        HashMap<String,Object> hashMap = new HashMap<>();
        hashMap.put(KEY_DATA,map);
        return JSON.toJSONString(hashMap);
    }

    public static String toJsonString(PowerPlc powerPlc){
        HashMap<String,Object> hashMap = new HashMap<>();
        hashMap.put(KEY_DATA,powerPlc);
        return JSON.toJSONString(hashMap);
    }

    public static String toJSONString(String message, int status, List<Map<String, Object>> list) {
        HashMap<String, Object> map = new HashMap<>();
        map.put(KEY_MSG, message);
        map.put(KEY_STATUS, status);
        map.put(KEY_DATA, list);
        return JSON.toJSONString(map);
    }

    public static String toWorkSheetString(String message, List<Map<String, Object>> unconfirmed, List<Map<String, Object>> processing) {
        HashMap<String, Object> map = new HashMap<>();
        map.put(KEY_MSG, message);
        map.put(KEY_STATUS, 200);
        map.put("unconfirmed", unconfirmed);
        map.put("processing", processing);
        return JSON.toJSONString(map);
    }

    public static String toUserString(String message, int status, List<User> list, int page) {
        HashMap<String, Object> map = new HashMap<>();
        map.put(KEY_MSG, message);
        map.put(KEY_STATUS, status);
        map.put(KEY_DATA, list);
        map.put("page", page);
        return JSON.toJSONString(map);
    }
    public static String toJsonString(Marker markerInfo) {
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("camera_latitude", markerInfo.getCamera_latitude());
        hashMap.put("camera_longitude", markerInfo.getCamera_longitude());
        hashMap.put("power_latitude", markerInfo.getPower_latitude());
        hashMap.put("power_longitude", markerInfo.getPower_longitude());
        return JSON.toJSONString(hashMap);
    }
}
