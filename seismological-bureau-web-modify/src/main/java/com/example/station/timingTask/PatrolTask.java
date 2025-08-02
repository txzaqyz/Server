package com.example.station.timingTask;

import com.example.station.entity.Equipment;
import com.example.station.mapper.EquipmentMapper;
import com.example.station.utils.PingUtils;
import com.example.station.utils.rtspTest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.Future;

@Component
@EnableAsync
public class PatrolTask {
    private static final Logger log = LoggerFactory.getLogger(PatrolTask.class);

    @Resource
    private EquipmentMapper equipmentMapper;

    /**
     * 功能：更新一个区域（市级）设备的网络状态，设计为异步是为了之后同时判断多个市区。
     * 因为没有返回值，不需要同步，故而不需要使用CompletableFuture类，仅作多设备网络判断提速使用
     * */
    @Async("detectionPool")
    public void connectDetection(List<Equipment> patrolDevices){
//        System.out.println(Thread.currentThread().getName());
        int patrolNum = patrolDevices.size();
        boolean[] deviceConnectStatus = new boolean[patrolNum];

        int count = 0;
        for(Equipment patrolDevice : patrolDevices){
            try {
                if(deviceConnectStatus[count]) continue;
                deviceConnectStatus[count] |= PingUtils.ping(patrolDevice.getIp());
                count++;
            }catch (IOException e){
                log.error("巡检异常",e);
            }
        }
        for(int i = 0; i < patrolNum; i++){
            Equipment patrolDeviceI = patrolDevices.get(i);
            if(deviceConnectStatus[i] != patrolDeviceI.getConnectionStatus()){
                equipmentMapper.updateConnectionStatus(patrolDeviceI.getId(),deviceConnectStatus[i]);
            }
        }
    }

    /**
     * 功能：实现视频设备数据实时性检查，因为视频流数据的判断相对耗时，采用异步方法。为了更好利用空闲时间，不采用按城市检测，采用直接检测。
     */
    @Async("expiredDataPool")
    public void rtspExpiredDataDetection(Equipment rtspDevice){
//        System.out.println(Thread.currentThread().getName());

        boolean available = rtspTest.rtspStreamingAvailable(rtspDevice.getEquipmentInfo());
//        System.out.println(rtspDevice.getEquipmentInfo()+" " + rtspDevice.getDataExpired());
        boolean res = !available;
        if(res != rtspDevice.getDataExpired()){
            equipmentMapper.updateIsDataExpired(rtspDevice.getId(),res);
        }

//        boolean[] deviceIsDataExpired = new boolean[rtspDevices.size()];
//
//        int count = 0;
//        for(Equipment rtspDevice : rtspDevices){
//            if(deviceIsDataExpired[count]) continue;
//            deviceIsDataExpired[count++] |= rtspTest.rtspStreamingAvailable(rtspDevice.getEquipmentInfo());
//        }
//
//        for(int i = 0; i < rtspDevices.size(); i++){
//            Equipment rtspDeviceI = rtspDevices.get(i);
//            if(deviceIsDataExpired[i] != rtspDeviceI.getDataExpired()){
//                equipmentMapper.updateIsDataExpired(rtspDeviceI.getId(),deviceIsDataExpired[i]);
//            }
//        }
    }
}
