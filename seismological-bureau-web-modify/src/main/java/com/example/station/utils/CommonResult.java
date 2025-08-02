package com.example.station.utils;

/**
 * @description:
 * @author: Stroke
 * @date: 2021/11/01
 */
public class CommonResult<T> {
    private static final int SUCCESS_CODE = 200;
    private static final int FAILED_CODE = 403;
    private static final int NO_AUTHORITY_CODE = 401;

    private String msg;
    private int status;
    private T data;
    private String token;

    public static CommonResult success(String msg) {
        return new CommonResult(msg, SUCCESS_CODE);
    }

    public static<T>  CommonResult<T> success(String msg, T data) {
        CommonResult<T> res = success(msg);
        res.data = data;
        return res;
    }

    public static CommonResult fail(String msg) {
        return new CommonResult(msg, FAILED_CODE);
    }

    public static CommonResult noPermission() {
        return new CommonResult("用户无权限", NO_AUTHORITY_CODE);
    }

    public CommonResult(String msg, int status) {
        this.msg = msg;
        this.status = status;
    }

    public CommonResult(String msg, int status, String token) {
        this.msg = msg;
        this.status = status;
        this.token = token;
    }

    public CommonResult() {
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}