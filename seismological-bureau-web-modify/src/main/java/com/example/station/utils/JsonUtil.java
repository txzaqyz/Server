package com.example.station.utils;

import com.cims.sync.exception.BusinessException;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.*;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * Json工具类
 *
 * @author yj
 * @date 2018/03/19
 */
public class JsonUtil {

    private final static ObjectMapper objectMapper;

    static {
        objectMapper = new ObjectMapper();
        objectMapper.setDefaultPropertyInclusion(
            JsonInclude.Value.construct(JsonInclude.Include.NON_NULL, JsonInclude.Include.ALWAYS));
        objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        objectMapper.disable(DeserializationFeature.FAIL_ON_IGNORED_PROPERTIES);
        objectMapper.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
        objectMapper.enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);
        objectMapper.enable(JsonParser.Feature.ALLOW_UNQUOTED_CONTROL_CHARS);
        objectMapper.enable(JsonParser.Feature.STRICT_DUPLICATE_DETECTION);
    }

    private JsonUtil() {
    }

    public static ObjectMapper getInstance() {
        return objectMapper;
    }

    /**
     * javaBean,list,array convert to json string
     */
    public static String obj2json(Object obj) {
        try {
            return objectMapper.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            throw new BusinessException(9001, "序列化异常", e);
        }
    }

    /**
     * json string convert to javaBean
     */
    public static <T> T json2pojo(String jsonStr, Class<T> clazz) {
        try {
            return objectMapper.readValue(jsonStr, clazz);
        } catch (IOException e) {
            throw new BusinessException(9001, "序列化异常", e);
        }
    }

    /**
     * json string convert to javaBean
     */
    public static <T> T json2pojo(String jsonStr, JavaType type) {
        try {
            return objectMapper.readValue(jsonStr, type);
        } catch (IOException e) {
            throw new BusinessException(9001, "序列化异常", e);
        }
    }


    /**
     * json string convert to map
     */
    public static Map<String, Object> json2map(String jsonStr) {
        return json2map(jsonStr, Object.class);
    }

    /**
     * json string convert to getOrDefault
     */
    public static Object json2OptMap(String jsonStr, String key, Object defaultValue) {
        Map<String, Object> map = com.example.station.utils.JsonUtil.json2map(jsonStr);
        Object obj = map.get(key);
        if (obj == null || obj == "") {
            obj = defaultValue;
        }
        return obj;
    }


    /**
     * json string convert to map with javaBean
     */
    public static <T> Map<String, T> json2map(String jsonStr, Class<T> clazz) {
        JavaType type =
                objectMapper.getTypeFactory().constructParametricType(Map.class, String.class, clazz);
        try {
            return objectMapper.readValue(jsonStr, type);
        } catch (IOException e) {
            throw new BusinessException(9001, "序列化异常", e);
        }
    }

    /**
     * json array string convert to list with javaBean
     */
    public static <T> List<T> json2list(String jsonArrayStr, Class<T> clazz) {
        JavaType type = objectMapper.getTypeFactory().constructParametricType(List.class, clazz);
        try {
            return objectMapper.readValue(jsonArrayStr, type);
        } catch (IOException e) {
            throw new BusinessException(9001, "序列化异常", e);
        }
    }

    /**
     * json array Object convert to list with javaBean
     */

    public static <T> List<T> json2list(String jsonArrayStr, JavaType javaType) {
        JavaType type = objectMapper.getTypeFactory().constructParametricType(List.class, javaType);
        try {
            return objectMapper.readValue(jsonArrayStr, type);
        } catch (IOException e) {
            throw new BusinessException(9001, "序列化异常", e);
        }
    }

    /**
     * javaBean convert to map
     */
    public static Map<String, Object> pojo2map(Object obj) {
        JavaType type =
                objectMapper.getTypeFactory().constructParametricType(Map.class, String.class, Object.class);
        return objectMapper.convertValue(obj, type);
    }

    /**
     * javaBean convert to map
     */
    public static <T> Map<String, T> pojo2map(Object obj, Class<T> clazz) {
        JavaType type =
            objectMapper.getTypeFactory().constructParametricType(Map.class, String.class, clazz);
        return objectMapper.convertValue(obj, type);
    }

    /**
     * json string convert to node
     */
    public static JsonNode json2Node(String json) {
        try {
            return objectMapper.readTree(json);
        } catch (IOException e) {
            throw new BusinessException(9001, "序列化异常", e);
        }
    }
}
