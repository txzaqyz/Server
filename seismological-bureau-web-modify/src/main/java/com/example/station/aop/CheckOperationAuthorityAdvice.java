package com.example.station.aop;

import com.example.station.utils.CommonResult;
import com.example.station.utils.JSONUtils;
import com.example.station.utils.JWTUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;

/**
 * @description:
 * @author: Stroke
 * @date: 2021/11/09
 */
@Aspect
@Component
public class CheckOperationAuthorityAdvice {

    @Pointcut("@annotation(com.example.station.annotation.CheckOperationAuthority)")
    private void checkOperationAuthority() {

    }

    @Around("checkOperationAuthority()")
    public Object checkOperationAuthority(ProceedingJoinPoint joinPoint) throws Throwable {
        Object[] objects = joinPoint.getArgs();
        HttpServletRequest request = (HttpServletRequest)objects[0];
        String token = JWTUtils.parseToken(request);
        int authority = JWTUtils.verifyAuthority(token);
        if (!hasOperationAuthority(authority)) {
            Signature signature = joinPoint.getSignature();
            if (signature instanceof MethodSignature) {
                MethodSignature methodSignature = (MethodSignature) signature;
                Method method = methodSignature.getMethod();
                Class<?> returnType = method.getReturnType();
                if (returnType == String.class) {
                    return JSONUtils.toJSONString("用户无权限", 401);
                } else if (returnType == CommonResult.class) {
                    return CommonResult.noPermission();
                } // 有其他类型可在这添加
            }
        }

        return joinPoint.proceed();
    }

    private boolean hasOperationAuthority(int authority) {
        return authority <= 1;
    }
}

