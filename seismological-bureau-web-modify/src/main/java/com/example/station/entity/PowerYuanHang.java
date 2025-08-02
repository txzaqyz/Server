package com.example.station.entity;

/**
 * 远航智能电源实体类
 */

public class PowerYuanHang {
    private Integer id;
    private String ups_id;
    private Integer ci;
    private String dt;
    private Integer vf;
    private Integer pva_state;
    private Double pva_V;
    private Integer pva_alarm_V;
    private Double pva_I;
    private Integer pva_alarm_I;
    private Integer pvb_state;
    private Double pvb_V;
    private Integer pvb_alarm_V;
    private Double pvb_I;
    private Integer pvb_alarm_I;
    private Double av;
    private Integer ba_ConnectState;
    private Integer ba_ChargeState;
    private Integer bb_ConnectState;
    private Integer bb_ChargeState;
    private Double pt;
    private Double ea_temperature;
    private Double ea_humidity;
    private Double eb_temperature;
    private Double eb_humidity;
    private Double ap;
    private Integer sv1_state;
    private Double sv1_V;
    private Double sv1_A;
    private Integer sv2_state;
    private Double sv2_V;
    private Double sv2_A;
    private Integer sv3_state;
    private Double sv3_V;
    private Double sv3_A;
    private Integer sv4_state;
    private Double sv4_V;
    private Double sv4_A;
    private Integer sv5_state;
    private Double sv5_V;
    private Double sv5_A;
    private Integer sv6_state;
    private Double sv6_V;
    private Double sv6_A;
    private Double vab_a;
    private Double vab_b;
    private Double a1_V;
    private Double a1_C;
    private Double a1_R;
    private Double a2_V;
    private Double a2_C;
    private Double a2_R;
    private Double a3_V;
    private Double a3_C;
    private Double a3_R;
    private Double a4_V;
    private Double a4_C;
    private Double a4_R;
    private Double a5_V;
    private Double a5_C;
    private Double a5_R;
    private Double a6_V;
    private Double a6_C;
    private Double a6_R;
    private Double b1_V;
    private Double b1_C;
    private Double b1_R;
    private Double b2_V;
    private Double b2_C;
    private Double b2_R;
    private Double b3_V;
    private Double b3_C;
    private Double b3_R;
    private Double b4_V;
    private Double b4_C;
    private Double b4_R;
    private Double b5_V;
    private Double b5_C;
    private Double b5_R;
    private Double b6_V;
    private Double b6_C;
    private Double b6_R;
    private Integer di_megnetic;
    private Integer di_smog;
    private Integer di_water;
    private Integer di_infrared;
    private Integer di_intelligence;
    private Integer iw_1;
    private Integer iw_2;
    private Integer iw_3;
    private Integer iw_4;
    private Integer iw_5;
    private Integer iw_6;
    private Integer tw_temperatureA;
    private Integer tw_temperatureB;
    private Integer tw_humidityA;
    private Integer tw_humidityB;
    private Integer tw_voltage;
    private Integer tw_power;
    private Integer tw_UnderVoltageA;
    private Integer tw_UnderVoltageB;
    private Integer oi_1;
    private Integer oi_2;
    private Integer oi_3;
    private Integer oi_4;
    private Integer oi_5;
    private Integer oi_6;
    private Double at;

    @Override
    public String toString() {
        return "PowerYuanHang{" +
                "id=" + id +
                ", ups_id='" + ups_id + '\'' +
                ", ci=" + ci +
                ", dt='" + dt + '\'' +
                ", vf=" + vf +
                ", pva_state=" + pva_state +
                ", pva_V=" + pva_V +
                ", pva_alarm_V=" + pva_alarm_V +
                ", pva_I=" + pva_I +
                ", pva_alarm_I=" + pva_alarm_I +
                ", pvb_state=" + pvb_state +
                ", pvb_V=" + pvb_V +
                ", pvb_alarm_V=" + pvb_alarm_V +
                ", pvb_I=" + pvb_I +
                ", pvb_alarm_I=" + pvb_alarm_I +
                ", av=" + av +
                ", ba_ConnectState=" + ba_ConnectState +
                ", ba_ChargeState=" + ba_ChargeState +
                ", bb_ConnectState=" + bb_ConnectState +
                ", bb_ChargeState=" + bb_ChargeState +
                ", pt=" + pt +
                ", ea_temperature=" + ea_temperature +
                ", ea_humidity=" + ea_humidity +
                ", eb_temperature=" + eb_temperature +
                ", eb_humidity=" + eb_humidity +
                ", ap=" + ap +
                ", sv1_state=" + sv1_state +
                ", sv1_V=" + sv1_V +
                ", sv1_A=" + sv1_A +
                ", sv2_state=" + sv2_state +
                ", sv2_V=" + sv2_V +
                ", sv2_A=" + sv2_A +
                ", sv3_state=" + sv3_state +
                ", sv3_V=" + sv3_V +
                ", sv3_A=" + sv3_A +
                ", sv4_state=" + sv4_state +
                ", sv4_V=" + sv4_V +
                ", sv4_A=" + sv4_A +
                ", sv5_state=" + sv5_state +
                ", sv5_V=" + sv5_V +
                ", sv5_A=" + sv5_A +
                ", sv6_state=" + sv6_state +
                ", sv6_V=" + sv6_V +
                ", sv6_A=" + sv6_A +
                ", vab_a=" + vab_a +
                ", vab_b=" + vab_b +
                ", a1_V=" + a1_V +
                ", a1_C=" + a1_C +
                ", a1_R=" + a1_R +
                ", a2_V=" + a2_V +
                ", a2_C=" + a2_C +
                ", a2_R=" + a2_R +
                ", a3_V=" + a3_V +
                ", a3_C=" + a3_C +
                ", a3_R=" + a3_R +
                ", a4_V=" + a4_V +
                ", a4_C=" + a4_C +
                ", a4_R=" + a4_R +
                ", a5_V=" + a5_V +
                ", a5_C=" + a5_C +
                ", a5_R=" + a5_R +
                ", a6_V=" + a6_V +
                ", a6_C=" + a6_C +
                ", a6_R=" + a6_R +
                ", b1_V=" + b1_V +
                ", b1_C=" + b1_C +
                ", b1_R=" + b1_R +
                ", b2_V=" + b2_V +
                ", b2_C=" + b2_C +
                ", b2_R=" + b2_R +
                ", b3_V=" + b3_V +
                ", b3_C=" + b3_C +
                ", b3_R=" + b3_R +
                ", b4_V=" + b4_V +
                ", b4_C=" + b4_C +
                ", b4_R=" + b4_R +
                ", b5_V=" + b5_V +
                ", b5_C=" + b5_C +
                ", b5_R=" + b5_R +
                ", b6_V=" + b6_V +
                ", b6_C=" + b6_C +
                ", b6_R=" + b6_R +
                ", di_megnetic=" + di_megnetic +
                ", di_smog=" + di_smog +
                ", di_water=" + di_water +
                ", di_infrared=" + di_infrared +
                ", di_intelligence=" + di_intelligence +
                ", iw_1=" + iw_1 +
                ", iw_2=" + iw_2 +
                ", iw_3=" + iw_3 +
                ", iw_4=" + iw_4 +
                ", iw_5=" + iw_5 +
                ", iw_6=" + iw_6 +
                ", tw_temperatureA=" + tw_temperatureA +
                ", tw_temperatureB=" + tw_temperatureB +
                ", tw_humidityA=" + tw_humidityA +
                ", tw_humidityB=" + tw_humidityB +
                ", tw_voltage=" + tw_voltage +
                ", tw_power=" + tw_power +
                ", tw_UnderVoltageA=" + tw_UnderVoltageA +
                ", tw_UnderVoltageB=" + tw_UnderVoltageB +
                ", oi_1=" + oi_1 +
                ", oi_2=" + oi_2 +
                ", oi_3=" + oi_3 +
                ", oi_4=" + oi_4 +
                ", oi_5=" + oi_5 +
                ", oi_6=" + oi_6 +
                ", at=" + at +
                '}';
    }

    public PowerYuanHang() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUps_id() {
        return ups_id;
    }

    public void setUps_id(String ups_id) {
        this.ups_id = ups_id;
    }

    public Integer getCi() {
        return ci;
    }

    public void setCi(Integer ci) {
        this.ci = ci;
    }

    public String getDt() {
        return dt;
    }

    public void setDt(String dt) {
        this.dt = dt;
    }

    public Integer getVf() {
        return vf;
    }

    public void setVf(Integer vf) {
        this.vf = vf;
    }

    public Integer getPva_state() {
        return pva_state;
    }

    public void setPva_state(Integer pva_state) {
        this.pva_state = pva_state;
    }

    public Double getPva_V() {
        return pva_V;
    }

    public void setPva_V(Double pva_V) {
        this.pva_V = pva_V;
    }

    public Integer getPva_alarm_V() {
        return pva_alarm_V;
    }

    public void setPva_alarm_V(Integer pva_alarm_V) {
        this.pva_alarm_V = pva_alarm_V;
    }

    public Double getPva_I() {
        return pva_I;
    }

    public void setPva_I(Double pva_I) {
        this.pva_I = pva_I;
    }

    public Integer getPva_alarm_I() {
        return pva_alarm_I;
    }

    public void setPva_alarm_I(Integer pva_alarm_I) {
        this.pva_alarm_I = pva_alarm_I;
    }

    public Integer getPvb_state() {
        return pvb_state;
    }

    public void setPvb_state(Integer pvb_state) {
        this.pvb_state = pvb_state;
    }

    public Double getPvb_V() {
        return pvb_V;
    }

    public void setPvb_V(Double pvb_V) {
        this.pvb_V = pvb_V;
    }

    public Integer getPvb_alarm_V() {
        return pvb_alarm_V;
    }

    public void setPvb_alarm_V(Integer pvb_alarm_V) {
        this.pvb_alarm_V = pvb_alarm_V;
    }

    public Double getPvb_I() {
        return pvb_I;
    }

    public void setPvb_I(Double pvb_I) {
        this.pvb_I = pvb_I;
    }

    public Integer getPvb_alarm_I() {
        return pvb_alarm_I;
    }

    public void setPvb_alarm_I(Integer pvb_alarm_I) {
        this.pvb_alarm_I = pvb_alarm_I;
    }

    public Double getAv() {
        return av;
    }

    public void setAv(Double av) {
        this.av = av;
    }

    public Integer getBa_ConnectState() {
        return ba_ConnectState;
    }

    public void setBa_ConnectState(Integer ba_ConnectState) {
        this.ba_ConnectState = ba_ConnectState;
    }

    public Integer getBa_ChargeState() {
        return ba_ChargeState;
    }

    public void setBa_ChargeState(Integer ba_ChargeState) {
        this.ba_ChargeState = ba_ChargeState;
    }

    public Integer getBb_ConnectState() {
        return bb_ConnectState;
    }

    public void setBb_ConnectState(Integer bb_ConnectState) {
        this.bb_ConnectState = bb_ConnectState;
    }

    public Integer getBb_ChargeState() {
        return bb_ChargeState;
    }

    public void setBb_ChargeState(Integer bb_ChargeState) {
        this.bb_ChargeState = bb_ChargeState;
    }

    public Double getPt() {
        return pt;
    }

    public void setPt(Double pt) {
        this.pt = pt;
    }

    public Double getEa_temperature() {
        return ea_temperature;
    }

    public void setEa_temperature(Double ea_temperature) {
        this.ea_temperature = ea_temperature;
    }

    public Double getEa_humidity() {
        return ea_humidity;
    }

    public void setEa_humidity(Double ea_humidity) {
        this.ea_humidity = ea_humidity;
    }

    public Double getEb_temperature() {
        return eb_temperature;
    }

    public void setEb_temperature(Double eb_temperature) {
        this.eb_temperature = eb_temperature;
    }

    public Double getEb_humidity() {
        return eb_humidity;
    }

    public void setEb_humidity(Double eb_humidity) {
        this.eb_humidity = eb_humidity;
    }

    public Double getAp() {
        return ap;
    }

    public void setAp(Double ap) {
        this.ap = ap;
    }

    public Integer getSv1_state() {
        return sv1_state;
    }

    public void setSv1_state(Integer sv1_state) {
        this.sv1_state = sv1_state;
    }

    public Double getSv1_V() {
        return sv1_V;
    }

    public void setSv1_V(Double sv1_V) {
        this.sv1_V = sv1_V;
    }

    public Double getSv1_A() {
        return sv1_A;
    }

    public void setSv1_A(Double sv1_A) {
        this.sv1_A = sv1_A;
    }

    public Integer getSv2_state() {
        return sv2_state;
    }

    public void setSv2_state(Integer sv2_state) {
        this.sv2_state = sv2_state;
    }

    public Double getSv2_V() {
        return sv2_V;
    }

    public void setSv2_V(Double sv2_V) {
        this.sv2_V = sv2_V;
    }

    public Double getSv2_A() {
        return sv2_A;
    }

    public void setSv2_A(Double sv2_A) {
        this.sv2_A = sv2_A;
    }

    public Integer getSv3_state() {
        return sv3_state;
    }

    public void setSv3_state(Integer sv3_state) {
        this.sv3_state = sv3_state;
    }

    public Double getSv3_V() {
        return sv3_V;
    }

    public void setSv3_V(Double sv3_V) {
        this.sv3_V = sv3_V;
    }

    public Double getSv3_A() {
        return sv3_A;
    }

    public void setSv3_A(Double sv3_A) {
        this.sv3_A = sv3_A;
    }

    public Integer getSv4_state() {
        return sv4_state;
    }

    public void setSv4_state(Integer sv4_state) {
        this.sv4_state = sv4_state;
    }

    public Double getSv4_V() {
        return sv4_V;
    }

    public void setSv4_V(Double sv4_V) {
        this.sv4_V = sv4_V;
    }

    public Double getSv4_A() {
        return sv4_A;
    }

    public void setSv4_A(Double sv4_A) {
        this.sv4_A = sv4_A;
    }

    public Integer getSv5_state() {
        return sv5_state;
    }

    public void setSv5_state(Integer sv5_state) {
        this.sv5_state = sv5_state;
    }

    public Double getSv5_V() {
        return sv5_V;
    }

    public void setSv5_V(Double sv5_V) {
        this.sv5_V = sv5_V;
    }

    public Double getSv5_A() {
        return sv5_A;
    }

    public void setSv5_A(Double sv5_A) {
        this.sv5_A = sv5_A;
    }

    public Integer getSv6_state() {
        return sv6_state;
    }

    public void setSv6_state(Integer sv6_state) {
        this.sv6_state = sv6_state;
    }

    public Double getSv6_V() {
        return sv6_V;
    }

    public void setSv6_V(Double sv6_V) {
        this.sv6_V = sv6_V;
    }

    public Double getSv6_A() {
        return sv6_A;
    }

    public void setSv6_A(Double sv6_A) {
        this.sv6_A = sv6_A;
    }

    public Double getVab_a() {
        return vab_a;
    }

    public void setVab_a(Double vab_a) {
        this.vab_a = vab_a;
    }

    public Double getVab_b() {
        return vab_b;
    }

    public void setVab_b(Double vab_b) {
        this.vab_b = vab_b;
    }

    public Double getA1_V() {
        return a1_V;
    }

    public void setA1_V(Double a1_V) {
        this.a1_V = a1_V;
    }

    public Double getA1_C() {
        return a1_C;
    }

    public void setA1_C(Double a1_C) {
        this.a1_C = a1_C;
    }

    public Double getA1_R() {
        return a1_R;
    }

    public void setA1_R(Double a1_R) {
        this.a1_R = a1_R;
    }

    public Double getA2_V() {
        return a2_V;
    }

    public void setA2_V(Double a2_V) {
        this.a2_V = a2_V;
    }

    public Double getA2_C() {
        return a2_C;
    }

    public void setA2_C(Double a2_C) {
        this.a2_C = a2_C;
    }

    public Double getA2_R() {
        return a2_R;
    }

    public void setA2_R(Double a2_R) {
        this.a2_R = a2_R;
    }

    public Double getA3_V() {
        return a3_V;
    }

    public void setA3_V(Double a3_V) {
        this.a3_V = a3_V;
    }

    public Double getA3_C() {
        return a3_C;
    }

    public void setA3_C(Double a3_C) {
        this.a3_C = a3_C;
    }

    public Double getA3_R() {
        return a3_R;
    }

    public void setA3_R(Double a3_R) {
        this.a3_R = a3_R;
    }

    public Double getA4_V() {
        return a4_V;
    }

    public void setA4_V(Double a4_V) {
        this.a4_V = a4_V;
    }

    public Double getA4_C() {
        return a4_C;
    }

    public void setA4_C(Double a4_C) {
        this.a4_C = a4_C;
    }

    public Double getA4_R() {
        return a4_R;
    }

    public void setA4_R(Double a4_R) {
        this.a4_R = a4_R;
    }

    public Double getA5_V() {
        return a5_V;
    }

    public void setA5_V(Double a5_V) {
        this.a5_V = a5_V;
    }

    public Double getA5_C() {
        return a5_C;
    }

    public void setA5_C(Double a5_C) {
        this.a5_C = a5_C;
    }

    public Double getA5_R() {
        return a5_R;
    }

    public void setA5_R(Double a5_R) {
        this.a5_R = a5_R;
    }

    public Double getA6_V() {
        return a6_V;
    }

    public void setA6_V(Double a6_V) {
        this.a6_V = a6_V;
    }

    public Double getA6_C() {
        return a6_C;
    }

    public void setA6_C(Double a6_C) {
        this.a6_C = a6_C;
    }

    public Double getA6_R() {
        return a6_R;
    }

    public void setA6_R(Double a6_R) {
        this.a6_R = a6_R;
    }

    public Double getB1_V() {
        return b1_V;
    }

    public void setB1_V(Double b1_V) {
        this.b1_V = b1_V;
    }

    public Double getB1_C() {
        return b1_C;
    }

    public void setB1_C(Double b1_C) {
        this.b1_C = b1_C;
    }

    public Double getB1_R() {
        return b1_R;
    }

    public void setB1_R(Double b1_R) {
        this.b1_R = b1_R;
    }

    public Double getB2_V() {
        return b2_V;
    }

    public void setB2_V(Double b2_V) {
        this.b2_V = b2_V;
    }

    public Double getB2_C() {
        return b2_C;
    }

    public void setB2_C(Double b2_C) {
        this.b2_C = b2_C;
    }

    public Double getB2_R() {
        return b2_R;
    }

    public void setB2_R(Double b2_R) {
        this.b2_R = b2_R;
    }

    public Double getB3_V() {
        return b3_V;
    }

    public void setB3_V(Double b3_V) {
        this.b3_V = b3_V;
    }

    public Double getB3_C() {
        return b3_C;
    }

    public void setB3_C(Double b3_C) {
        this.b3_C = b3_C;
    }

    public Double getB3_R() {
        return b3_R;
    }

    public void setB3_R(Double b3_R) {
        this.b3_R = b3_R;
    }

    public Double getB4_V() {
        return b4_V;
    }

    public void setB4_V(Double b4_V) {
        this.b4_V = b4_V;
    }

    public Double getB4_C() {
        return b4_C;
    }

    public void setB4_C(Double b4_C) {
        this.b4_C = b4_C;
    }

    public Double getB4_R() {
        return b4_R;
    }

    public void setB4_R(Double b4_R) {
        this.b4_R = b4_R;
    }

    public Double getB5_V() {
        return b5_V;
    }

    public void setB5_V(Double b5_V) {
        this.b5_V = b5_V;
    }

    public Double getB5_C() {
        return b5_C;
    }

    public void setB5_C(Double b5_C) {
        this.b5_C = b5_C;
    }

    public Double getB5_R() {
        return b5_R;
    }

    public void setB5_R(Double b5_R) {
        this.b5_R = b5_R;
    }

    public Double getB6_V() {
        return b6_V;
    }

    public void setB6_V(Double b6_V) {
        this.b6_V = b6_V;
    }

    public Double getB6_C() {
        return b6_C;
    }

    public void setB6_C(Double b6_C) {
        this.b6_C = b6_C;
    }

    public Double getB6_R() {
        return b6_R;
    }

    public void setB6_R(Double b6_R) {
        this.b6_R = b6_R;
    }

    public Integer getDi_megnetic() {
        return di_megnetic;
    }

    public void setDi_megnetic(Integer di_megnetic) {
        this.di_megnetic = di_megnetic;
    }

    public Integer getDi_smog() {
        return di_smog;
    }

    public void setDi_smog(Integer di_smog) {
        this.di_smog = di_smog;
    }

    public Integer getDi_water() {
        return di_water;
    }

    public void setDi_water(Integer di_water) {
        this.di_water = di_water;
    }

    public Integer getDi_infrared() {
        return di_infrared;
    }

    public void setDi_infrared(Integer di_infrared) {
        this.di_infrared = di_infrared;
    }

    public Integer getDi_intelligence() {
        return di_intelligence;
    }

    public void setDi_intelligence(Integer di_intelligence) {
        this.di_intelligence = di_intelligence;
    }

    public Integer getIw_1() {
        return iw_1;
    }

    public void setIw_1(Integer iw_1) {
        this.iw_1 = iw_1;
    }

    public Integer getIw_2() {
        return iw_2;
    }

    public void setIw_2(Integer iw_2) {
        this.iw_2 = iw_2;
    }

    public Integer getIw_3() {
        return iw_3;
    }

    public void setIw_3(Integer iw_3) {
        this.iw_3 = iw_3;
    }

    public Integer getIw_4() {
        return iw_4;
    }

    public void setIw_4(Integer iw_4) {
        this.iw_4 = iw_4;
    }

    public Integer getIw_5() {
        return iw_5;
    }

    public void setIw_5(Integer iw_5) {
        this.iw_5 = iw_5;
    }

    public Integer getIw_6() {
        return iw_6;
    }

    public void setIw_6(Integer iw_6) {
        this.iw_6 = iw_6;
    }

    public Integer getTw_temperatureA() {
        return tw_temperatureA;
    }

    public void setTw_temperatureA(Integer tw_temperatureA) {
        this.tw_temperatureA = tw_temperatureA;
    }

    public Integer getTw_temperatureB() {
        return tw_temperatureB;
    }

    public void setTw_temperatureB(Integer tw_temperatureB) {
        this.tw_temperatureB = tw_temperatureB;
    }

    public Integer getTw_humidityA() {
        return tw_humidityA;
    }

    public void setTw_humidityA(Integer tw_humidityA) {
        this.tw_humidityA = tw_humidityA;
    }

    public Integer getTw_humidityB() {
        return tw_humidityB;
    }

    public void setTw_humidityB(Integer tw_humidityB) {
        this.tw_humidityB = tw_humidityB;
    }

    public Integer getTw_voltage() {
        return tw_voltage;
    }

    public void setTw_voltage(Integer tw_voltage) {
        this.tw_voltage = tw_voltage;
    }

    public Integer getTw_power() {
        return tw_power;
    }

    public void setTw_power(Integer tw_power) {
        this.tw_power = tw_power;
    }

    public Integer getTw_UnderVoltageA() {
        return tw_UnderVoltageA;
    }

    public void setTw_UnderVoltageA(Integer tw_UnderVoltageA) {
        this.tw_UnderVoltageA = tw_UnderVoltageA;
    }

    public Integer getTw_UnderVoltageB() {
        return tw_UnderVoltageB;
    }

    public void setTw_UnderVoltageB(Integer tw_UnderVoltageB) {
        this.tw_UnderVoltageB = tw_UnderVoltageB;
    }

    public Integer getOi_1() {
        return oi_1;
    }

    public void setOi_1(Integer oi_1) {
        this.oi_1 = oi_1;
    }

    public Integer getOi_2() {
        return oi_2;
    }

    public void setOi_2(Integer oi_2) {
        this.oi_2 = oi_2;
    }

    public Integer getOi_3() {
        return oi_3;
    }

    public void setOi_3(Integer oi_3) {
        this.oi_3 = oi_3;
    }

    public Integer getOi_4() {
        return oi_4;
    }

    public void setOi_4(Integer oi_4) {
        this.oi_4 = oi_4;
    }

    public Integer getOi_5() {
        return oi_5;
    }

    public void setOi_5(Integer oi_5) {
        this.oi_5 = oi_5;
    }

    public Integer getOi_6() {
        return oi_6;
    }

    public void setOi_6(Integer oi_6) {
        this.oi_6 = oi_6;
    }

    public Double getAt() {
        return at;
    }

    public void setAt(Double at) {
        this.at = at;
    }

    public PowerYuanHang(Integer id, String ups_id, Integer ci, String dt, Integer vf, Integer pva_state, Double pva_V, Integer pva_alarm_V, Double pva_I, Integer pva_alarm_I, Integer pvb_state, Double pvb_V, Integer pvb_alarm_V, Double pvb_I, Integer pvb_alarm_I, Double av, Integer ba_ConnectState, Integer ba_ChargeState, Integer bb_ConnectState, Integer bb_ChargeState, Double pt, Double ea_temperature, Double ea_humidity, Double eb_temperature, Double eb_humidity, Double ap, Integer sv1_state, Double sv1_V, Double sv1_A, Integer sv2_state, Double sv2_V, Double sv2_A, Integer sv3_state, Double sv3_V, Double sv3_A, Integer sv4_state, Double sv4_V, Double sv4_A, Integer sv5_state, Double sv5_V, Double sv5_A, Integer sv6_state, Double sv6_V, Double sv6_A, Double vab_a, Double vab_b, Double a1_V, Double a1_C, Double a1_R, Double a2_V, Double a2_C, Double a2_R, Double a3_V, Double a3_C, Double a3_R, Double a4_V, Double a4_C, Double a4_R, Double a5_V, Double a5_C, Double a5_R, Double a6_V, Double a6_C, Double a6_R, Double b1_V, Double b1_C, Double b1_R, Double b2_V, Double b2_C, Double b2_R, Double b3_V, Double b3_C, Double b3_R, Double b4_V, Double b4_C, Double b4_R, Double b5_V, Double b5_C, Double b5_R, Double b6_V, Double b6_C, Double b6_R, Integer di_megnetic, Integer di_smog, Integer di_water, Integer di_infrared, Integer di_intelligence, Integer iw_1, Integer iw_2, Integer iw_3, Integer iw_4, Integer iw_5, Integer iw_6, Integer tw_temperatureA, Integer tw_temperatureB, Integer tw_humidityA, Integer tw_humidityB, Integer tw_voltage, Integer tw_power, Integer tw_UnderVoltageA, Integer tw_UnderVoltageB, Integer oi_1, Integer oi_2, Integer oi_3, Integer oi_4, Integer oi_5, Integer oi_6, Double at) {
        this.id = id;
        this.ups_id = ups_id;
        this.ci = ci;
        this.dt = dt;
        this.vf = vf;
        this.pva_state = pva_state;
        this.pva_V = pva_V;
        this.pva_alarm_V = pva_alarm_V;
        this.pva_I = pva_I;
        this.pva_alarm_I = pva_alarm_I;
        this.pvb_state = pvb_state;
        this.pvb_V = pvb_V;
        this.pvb_alarm_V = pvb_alarm_V;
        this.pvb_I = pvb_I;
        this.pvb_alarm_I = pvb_alarm_I;
        this.av = av;
        this.ba_ConnectState = ba_ConnectState;
        this.ba_ChargeState = ba_ChargeState;
        this.bb_ConnectState = bb_ConnectState;
        this.bb_ChargeState = bb_ChargeState;
        this.pt = pt;
        this.ea_temperature = ea_temperature;
        this.ea_humidity = ea_humidity;
        this.eb_temperature = eb_temperature;
        this.eb_humidity = eb_humidity;
        this.ap = ap;
        this.sv1_state = sv1_state;
        this.sv1_V = sv1_V;
        this.sv1_A = sv1_A;
        this.sv2_state = sv2_state;
        this.sv2_V = sv2_V;
        this.sv2_A = sv2_A;
        this.sv3_state = sv3_state;
        this.sv3_V = sv3_V;
        this.sv3_A = sv3_A;
        this.sv4_state = sv4_state;
        this.sv4_V = sv4_V;
        this.sv4_A = sv4_A;
        this.sv5_state = sv5_state;
        this.sv5_V = sv5_V;
        this.sv5_A = sv5_A;
        this.sv6_state = sv6_state;
        this.sv6_V = sv6_V;
        this.sv6_A = sv6_A;
        this.vab_a = vab_a;
        this.vab_b = vab_b;
        this.a1_V = a1_V;
        this.a1_C = a1_C;
        this.a1_R = a1_R;
        this.a2_V = a2_V;
        this.a2_C = a2_C;
        this.a2_R = a2_R;
        this.a3_V = a3_V;
        this.a3_C = a3_C;
        this.a3_R = a3_R;
        this.a4_V = a4_V;
        this.a4_C = a4_C;
        this.a4_R = a4_R;
        this.a5_V = a5_V;
        this.a5_C = a5_C;
        this.a5_R = a5_R;
        this.a6_V = a6_V;
        this.a6_C = a6_C;
        this.a6_R = a6_R;
        this.b1_V = b1_V;
        this.b1_C = b1_C;
        this.b1_R = b1_R;
        this.b2_V = b2_V;
        this.b2_C = b2_C;
        this.b2_R = b2_R;
        this.b3_V = b3_V;
        this.b3_C = b3_C;
        this.b3_R = b3_R;
        this.b4_V = b4_V;
        this.b4_C = b4_C;
        this.b4_R = b4_R;
        this.b5_V = b5_V;
        this.b5_C = b5_C;
        this.b5_R = b5_R;
        this.b6_V = b6_V;
        this.b6_C = b6_C;
        this.b6_R = b6_R;
        this.di_megnetic = di_megnetic;
        this.di_smog = di_smog;
        this.di_water = di_water;
        this.di_infrared = di_infrared;
        this.di_intelligence = di_intelligence;
        this.iw_1 = iw_1;
        this.iw_2 = iw_2;
        this.iw_3 = iw_3;
        this.iw_4 = iw_4;
        this.iw_5 = iw_5;
        this.iw_6 = iw_6;
        this.tw_temperatureA = tw_temperatureA;
        this.tw_temperatureB = tw_temperatureB;
        this.tw_humidityA = tw_humidityA;
        this.tw_humidityB = tw_humidityB;
        this.tw_voltage = tw_voltage;
        this.tw_power = tw_power;
        this.tw_UnderVoltageA = tw_UnderVoltageA;
        this.tw_UnderVoltageB = tw_UnderVoltageB;
        this.oi_1 = oi_1;
        this.oi_2 = oi_2;
        this.oi_3 = oi_3;
        this.oi_4 = oi_4;
        this.oi_5 = oi_5;
        this.oi_6 = oi_6;
        this.at = at;
    }
}
