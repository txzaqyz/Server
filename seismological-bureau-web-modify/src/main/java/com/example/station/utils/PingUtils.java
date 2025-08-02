package com.example.station.utils;

import java.io.IOException;
import java.net.InetAddress;

public class  PingUtils {
    public static boolean ping(String ipAddress) throws IOException {
        // 默认3秒以上算超时
        int  timeOut =  500 ;
        boolean status = InetAddress.getByName(ipAddress).isReachable(timeOut);
        return status;
    }
}
