package com.example.station.sso;


import com.alibaba.druid.util.StringUtils;
import com.alibaba.fastjson.JSONObject;

import com.example.station.config.SSOClientConfig;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.MapUtils;
import org.apache.ibatis.util.MapUtil;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.*;

/**
 * 长威单点登入模块
 *
 * @author keyz
 * @since 1.0
 */
public class XindunRquestTemplate {


    private SSOClientConfig ssoClientConfig;

    private RestTemplate restTemplate;


    public XindunRquestTemplate(SSOClientConfig ssoClientConfig) {
        this.ssoClientConfig = ssoClientConfig;
    }



    public XindunAccessGrant exchangeForAccess(String authorizationCode) {
        StringBuilder accessTokenRequestUrl = new StringBuilder(ssoClientConfig.getAccessTokenUri());
        accessTokenRequestUrl.append("?client_id=" + ssoClientConfig.getClientId());
        accessTokenRequestUrl.append("&client_secret=" + ssoClientConfig.getClientSecret());
        accessTokenRequestUrl.append("&code=" + authorizationCode);
        accessTokenRequestUrl.append("&grant_type=authorization_code");
        accessTokenRequestUrl.append("&redirect_uri=" + ssoClientConfig.getRedirectUri());
        return getRestTemplate(accessTokenRequestUrl);
    }


    public String getAccount(XindunAccessGrant accessGrant) {
        StringBuilder userUrl = new StringBuilder(ssoClientConfig.getUserInfoUri());
        userUrl.append("?access_token=" + accessGrant.getAccessToken());
        String result = getRestTemplate().getForObject(userUrl.toString(), String.class);
        JSONObject json = JSONObject.parseObject(result);
        String username = json.getString("account");
        return username;
    }

    private XindunAccessGrant getRestTemplate(StringBuilder accessTokenRequestUrl) {

        String response = null;

        Map<String, Object> result = null;
        try {
            response = getRestTemplate().postForObject(accessTokenRequestUrl.toString(),null, String.class);
            result = new ObjectMapper().readValue(response, Map.class);
        } catch (Exception e) {
            return null;
        }

        //返回错误码时直接返回空
        if (!StringUtils.isEmpty(MapUtils.getString(result, "error"))) {
            String errmsg = MapUtils.getString(result, "error");
            throw new RuntimeException("获取access token失败, error:" + errmsg);
        }

        if (MapUtils.getBoolean(result, "success") != null && !MapUtils.getBoolean(result, "success")) {
            String errmsg = MapUtils.getString(result, "msg");
            throw new RuntimeException(errmsg);
        }

        XindunAccessGrant accessToken = new XindunAccessGrant(
                MapUtils.getString(result, "access_token"),
                MapUtils.getString(result, "scope"),
                MapUtils.getString(result, "refresh_token"),
                MapUtils.getLong(result, "expires_in"),
                MapUtils.getString(result, "token_type"));

        return accessToken;
    }

    private RestTemplate getRestTemplate() {
        this.restTemplate = this.createRestTemplate();
        return this.restTemplate;
    }

    private RestTemplate createRestTemplate() {
        ClientHttpRequestFactory requestFactory = new SSL();
        ((SSL) requestFactory).setConnectTimeout(60000);
        ((SSL) requestFactory).setReadTimeout(60000);
        RestTemplate restTemplate = new RestTemplate(requestFactory);
        restTemplate.getMessageConverters().set(1, new StringHttpMessageConverter(StandardCharsets.UTF_8));
        return restTemplate;
    }

}
