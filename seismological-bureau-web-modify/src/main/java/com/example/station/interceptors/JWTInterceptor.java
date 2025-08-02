package com.example.station.interceptors;

import com.auth0.jwt.exceptions.AlgorithmMismatchException;
import com.auth0.jwt.exceptions.InvalidClaimException;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.example.station.utils.JSONUtils;
import com.example.station.utils.JWTUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class JWTInterceptor implements HandlerInterceptor {
    private static final Logger logger = LoggerFactory.getLogger(com.example.station.interceptors.JWTInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String requestPath = request.getRequestURI();
        // 判断请求路径是否为 /push/org 或 /push/account
        System.out.println("*************");
        System.out.println(requestPath);
        System.out.println("***********");

        if ("/push/org".equals(requestPath) || "/push/account".equals(requestPath)) {
            return true;  // 直接放行
        }
        String code = request.getParameter("code");
        // 判断参数是否为空
        if (code != null && !code.isEmpty()) {
            // 参数不为空，进行相应的处理
            System.out.println("code：" + code);
            return true;
        }

//        获取请求头中的令牌
        String token = request.getHeader("token");
        String json;
        try {
//          验证token合法性
            JWTUtils.tokenVerify(token);
            return true;
        } catch (SignatureVerificationException e) {
            json = JSONUtils.toJSONString("无效签名", 401);
        } catch (TokenExpiredException e) {
            json = JSONUtils.toJSONString("token过期", 401);
        } catch (AlgorithmMismatchException e) {
            json = JSONUtils.toJSONString("token算法不一致", 401);
        } catch (InvalidClaimException e) {
            json = JSONUtils.toJSONString("token格式无效", 401);
        } catch (Exception e) {
            logger.error("拦截器发生的服务器异常",e);
            json = JSONUtils.toJSONString("服务器错误", 500);
        }
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().println(json);
        return false;
    }
}
