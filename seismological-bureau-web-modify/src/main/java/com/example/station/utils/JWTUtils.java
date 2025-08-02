package com.example.station.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.station.entity.User;

import javax.servlet.http.HttpServletRequest;
import java.util.Calendar;

/**
 * 生成和解析token的工具类
 */
public class JWTUtils {
    private static final String SIGN = "@%$*%@()ASDqwer";
    // keys of token
    private static final String KEY_ID = "id";
    private static final String KEY_STAFF = "staff";
    private static final String KEY_AUTHORITY = "authority";
    private static final String KEY_AREA = "area";
    private static final String KEY_REGION = "region";

//    登陆token
    public static String creatToken(User user){
        Calendar instance = Calendar.getInstance();
        instance.add(Calendar.DATE,1);

        JWTCreator.Builder jwt = JWT.create();
        jwt.withClaim(KEY_ID, user.getId());
        jwt.withClaim(KEY_STAFF, user.getStaff());
        jwt.withClaim(KEY_AUTHORITY, user.getAuthority());
        jwt.withClaim(KEY_AREA, user.getArea_level());
        jwt.withClaim(KEY_REGION, user.getRegion_id());
        String token = jwt.withExpiresAt(instance.getTime()).sign(Algorithm.HMAC256(SIGN));
        return token;
    }

    // 从请求头中拿取token
    public static String parseToken(HttpServletRequest request) {
        return request.getHeader("token");
    }

    // 验证token
    public static DecodedJWT tokenVerify(String token){
        return JWT.require(Algorithm.HMAC256(SIGN)).build().verify(token);
    }

    public static String parseStaff(String token){
        DecodedJWT verify = tokenVerify(token);
        return verify.getClaim(KEY_STAFF).asString();
    }

    public static int verifyAuthority(String token) {
        DecodedJWT verify = tokenVerify(token);
        return verify.getClaim(KEY_AUTHORITY).asInt();
    }

    public static int parseId(String token){
        DecodedJWT verify = tokenVerify(token);
        return verify.getClaim(KEY_ID).asInt();
    }

    public static int parseRegionId(String token){
        DecodedJWT verify = tokenVerify(token);
        return verify.getClaim(KEY_REGION).asInt();
    }

    public static int parseAreaLevel(String token){
        DecodedJWT verify = tokenVerify(token);
        return verify.getClaim(KEY_AREA).asInt();
    }

}
