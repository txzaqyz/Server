package com.example.station.utils;

import com.example.station.entity.PowerYuanHang;
import com.example.station.entity.YuanHangWebSimpleData;

public class PowerYuanHangToWebHandleUtils {
    public static int checkAlarm(PowerYuanHang powerYuanHang){
        if (powerYuanHang.getIw_1() == 1 || powerYuanHang.getIw_2() == 1 || powerYuanHang.getIw_3() == 1 || powerYuanHang.getIw_4() == 1 || powerYuanHang.getIw_5() == 1 || powerYuanHang.getIw_6() == 1){
            return 1;
        }
        if (powerYuanHang.getTw_temperatureA() == 11 || powerYuanHang.getTw_temperatureB() == 21 || powerYuanHang.getTw_temperatureA() == -11 || powerYuanHang.getTw_temperatureB() == -21){
            return 1;
        }
        if(powerYuanHang.getTw_humidityA() == 11 || powerYuanHang.getTw_humidityB() == 21 || powerYuanHang.getTw_voltage() == 1 || powerYuanHang.getTw_power() == 1 || powerYuanHang.getTw_UnderVoltageA() == 1 || powerYuanHang.getTw_UnderVoltageB() == 1){
            return 1;
        }
        if(powerYuanHang.getOi_1() == 1 || powerYuanHang.getOi_2() == 1 || powerYuanHang.getOi_3() == 1 || powerYuanHang.getOi_4() == 1 || powerYuanHang.getOi_5() == 1 || powerYuanHang.getOi_6() == 1){
            return 1;
        }
        return 0;
    }

    public static void creatData(YuanHangWebSimpleData data, PowerYuanHang power){
        if(power != null) {
            data.setAcVoltage(power.getAv());
            data.setPowerTemperature(power.getPt());
            data.setBatteryA(power.getVab_a());
            data.setBatteryB(power.getVab_b());
            data.setTemperature(power.getEa_temperature());
            data.setHumidity(power.getEa_humidity());
            data.setTime(power.getDt());
            data.setAlarm(PowerYuanHangToWebHandleUtils.checkAlarm(power));
        }
    }
}
