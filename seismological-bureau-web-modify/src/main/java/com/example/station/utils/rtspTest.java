package com.example.station.utils;

import com.example.station.timingTask.PatrolTask;
import org.bytedeco.ffmpeg.global.avutil;
import org.bytedeco.javacv.FFmpegFrameGrabber;
import org.bytedeco.javacv.Frame;
import org.bytedeco.javacv.FrameGrabber;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class rtspTest {
    private static final Logger log = LoggerFactory.getLogger(PatrolTask.class);
    //RW_TIMEOUT本来是想做获取数据的延迟时间，暂时没用。
    private static enum TimeoutOption {
        TIMEOUT,
        RW_TIMEOUT;
        public String getKey() {
            return toString().toLowerCase();
        }
    }

    //5秒连接延迟
    private static final int TIMEOUT = 1;

    public static boolean rtspStreamingAvailable(String rtspAddress) {

//        System.out.println("start:"+System.currentTimeMillis());
        FFmpegFrameGrabber grabber = new FFmpegFrameGrabber(rtspAddress);
        boolean flag = false;

        try {
            grabber.setOption(
                    TimeoutOption.TIMEOUT.getKey(),
                    String.valueOf(TIMEOUT * 1000000)
            );
            avutil.av_log_set_level(avutil.AV_LOG_ERROR);
            grabber.startUnsafe();
//            System.out.println("middle:"+System.currentTimeMillis());

            Frame frame = null;
            //TODO:此处之后可以加上延迟，这里没有加
            for(int i = 0; i < 5; i++) {
                if ((frame = grabber.grab()) != null) {
                    flag = true;
                    break;
                }
            }

        } catch (FrameGrabber.Exception ex) {

        }finally {
            try {
                grabber.close();
            }catch (Exception e){
                log.error("grabber关闭异常",e);
            }
//            System.out.println("end:"+System.currentTimeMillis());
            return flag;
        }
    }

}
