package com.example.station.timingTask;

import com.example.station.entity.Equipment;
import com.example.station.entity.Region;
import com.example.station.entity.WorkSheet;
import com.example.station.mapper.EquipmentMapper;
import com.example.station.mapper.StationMapper;
import com.example.station.mapper.WorkSheetMapper;
import com.example.station.utils.PingUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;


/**
 * 此类用于定时巡检硬件设备
 */
@Component
public class DetectionTask {
    private static final Logger log = LoggerFactory.getLogger(DetectionTask.class);

    @Resource
    private PatrolTask patrolTask;

    @Resource
    private EquipmentMapper equipmentMapper;
    @Resource
    private WorkSheetMapper workSheetMapper;
    @Resource
    private StationMapper stationMapper;
    //前3个数字分别为秒、分、时
    @Scheduled(cron = "0 0 0 * * *")
    @Transactional
    void artificialDetection() {
        List<Equipment> list = equipmentMapper.getDetectionEquipment();
        for (Equipment equipment : list) {
            boolean flag = false;
            //ping5次，5次都失败则认为设备故障
            for (int i = 0; i < 5; i++) {
                try {
                    flag = PingUtils.ping(equipment.getIp());
                    System.out.println("执行第"+i+"次ping");
                    if (flag==true){
                        break;
                    }
                } catch (IOException e) {
                    log.error("自动巡检异常",e);
                }
            }
            if (flag == false) {
                Timestamp updateTime = new Timestamp(new Date().getTime());
                WorkSheet workSheet = new WorkSheet();
                Region region = stationMapper.getEquipmentAuthority(equipment.getStationId());
                workSheet.setNumber(equipment.getNumber());
                workSheet.setName(equipment.getName());
                workSheet.setInformation("定时巡检");
                workSheet.setAreaLevel(region.getLevel());
                workSheet.setRegionId(region.getRegionId());
                workSheet.setCreateTime(updateTime);
                workSheet.setState(0);
                workSheet.setInitiator(0);
                workSheetMapper.creatWorkSheet(workSheet);
                equipmentMapper.updateEquipmentStateById(equipment.getId(), 1, updateTime, 0);
            }
        }
    }

    //暂定60s更新一次
    @Scheduled(fixedDelay = 60000)
    public void patrolDetection(){
//        LoggerFactory.getLogger(DetectionTask.class).info("Detection Start");
        List<Equipment> patrolDevicesList = equipmentMapper.getPatrolDevice(2);//目前只有一个城市的巡检,之后拓展对江苏的所有市id写一个循环即可
        patrolTask.connectDetection(patrolDevicesList);
    }

    @Scheduled(fixedDelay = 300000)
    public void dataExpiredDetection(){
        List<Equipment> patrolDevicesList = equipmentMapper.getPatrolDevice(2);//只对盐城设备进行判断
        equipmentMapper.updateDataExpiredStatusOfPlc(3);
        equipmentMapper.updateDataExpiredStatusOfPower(2);
        for(Equipment patrolDevice : patrolDevicesList){
            if(patrolDevice.getEquipmentType() == 1){
                patrolTask.rtspExpiredDataDetection(patrolDevice);
            }
        }
    }
}
