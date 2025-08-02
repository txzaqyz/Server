package com.example.station.sso;



public class XindunAccessGrant {
    private String accessToken;

    private String refreshToken;

    private String tokenType;

    private String scope;

    private Long expiresTime;

    public XindunAccessGrant(String accessToken, String scope, String refreshToken, Long expiresIn, String tokenType) {
        this.accessToken = accessToken;
        this.scope = scope;
        this.refreshToken = refreshToken;
        this.tokenType=tokenType;
        this.expiresTime = expiresIn != null ? System.currentTimeMillis() + expiresIn * 1000L : null;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public String getTokenType() {
        return tokenType;
    }

    public void setTokenType(String tokenType) {
        this.tokenType = tokenType;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public Long getExpiresTime() {
        return expiresTime;
    }

    public void setExpiresTime(Long expiresTime) {
        this.expiresTime = expiresTime;
    }
}
