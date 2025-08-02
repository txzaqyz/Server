package com.example.station.entity;



public class EquipmentRegister {
    private int id;
    private  String number;
    private  String device_code;
    private  String observe_code;
    private  String type_code;
    private  String device_model;
    private  String brand;
    private  String serial;
    private  String version;
    private  String life;
    private  int is_import;
    private  int is_final;
    private  int is_produce;

    private String produce_time;
    private  String warranty_time;
    private  String manu_code;
    private  String manu_name;
    private  String manu_address;
    private  String manu_zip;
    private  String manu_contact;
    private  String manu_phone;
    private  String manu_email;
    private  String sup_code;
    private  String sup_name;
    private  String sup_zip;
    private  String sup_contact;
    private  String sup_phone;
    private  String sup_email;
    private  int operator;

    public EquipmentRegister(int id, String number, String device_code, String observe_code, String type_code, String device_model, String brand, String serial, String version, String life, int is_import, int is_final, int is_produce, String produce_time, String warranty_time, String manu_code, String manu_name, String manu_address, String manu_zip, String manu_contact, String manu_phone, String manu_email, String sup_code, String sup_name, String sup_zip, String sup_contact, String sup_phone, String sup_email, int operator) {
        this.id = id;
        this.number = number;
        this.device_code = device_code;
        this.observe_code = observe_code;
        this.type_code = type_code;
        this.device_model = device_model;
        this.brand = brand;
        this.serial = serial;
        this.version = version;
        this.life = life;
        this.is_import = is_import;
        this.is_final = is_final;
        this.is_produce = is_produce;
        this.produce_time = produce_time;
        this.warranty_time = warranty_time;
        this.manu_code = manu_code;
        this.manu_name = manu_name;
        this.manu_address = manu_address;
        this.manu_zip = manu_zip;
        this.manu_contact = manu_contact;
        this.manu_phone = manu_phone;
        this.manu_email = manu_email;
        this.sup_code = sup_code;
        this.sup_name = sup_name;
        this.sup_zip = sup_zip;
        this.sup_contact = sup_contact;
        this.sup_phone = sup_phone;
        this.sup_email = sup_email;
        this.operator = operator;
    }

    @Override
    public String toString() {
        return "EquipmentRegister{" +
                "id=" + id +
                ", number='" + number + '\'' +
                ", device_code='" + device_code + '\'' +
                ", observe_code='" + observe_code + '\'' +
                ", type_code='" + type_code + '\'' +
                ", device_model='" + device_model + '\'' +
                ", brand='" + brand + '\'' +
                ", serial='" + serial + '\'' +
                ", version='" + version + '\'' +
                ", life='" + life + '\'' +
                ", is_import=" + is_import +
                ", is_final=" + is_final +
                ", is_produce=" + is_produce +
                ", produce_time='" + produce_time + '\'' +
                ", warranty_time='" + warranty_time + '\'' +
                ", manu_code='" + manu_code + '\'' +
                ", manu_name='" + manu_name + '\'' +
                ", manu_address='" + manu_address + '\'' +
                ", manu_zip='" + manu_zip + '\'' +
                ", manu_contact='" + manu_contact + '\'' +
                ", manu_phone='" + manu_phone + '\'' +
                ", manu_email='" + manu_email + '\'' +
                ", sup_code='" + sup_code + '\'' +
                ", sup_name='" + sup_name + '\'' +
                ", sup_zip='" + sup_zip + '\'' +
                ", sup_contact='" + sup_contact + '\'' +
                ", sup_phone='" + sup_phone + '\'' +
                ", sup_email='" + sup_email + '\'' +
                ", operator=" + operator +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getObserve_code() {
        return observe_code;
    }

    public void setObserve_code(String observe_code) {
        this.observe_code = observe_code;
    }

    public String getType_code() {
        return type_code;
    }

    public void setType_code(String type_code) {
        this.type_code = type_code;
    }

    public String getDevice_model() {
        return device_model;
    }

    public void setDevice_model(String device_model) {
        this.device_model = device_model;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getSerial() {
        return serial;
    }

    public void setSerial(String serial) {
        this.serial = serial;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getLife() {
        return life;
    }

    public void setLife(String life) {
        this.life = life;
    }

    public int getIs_import() {
        return is_import;
    }

    public void setIs_import(int is_import) {
        this.is_import = is_import;
    }

    public int getIs_final() {
        return is_final;
    }

    public void setIs_final(int is_final) {
        this.is_final = is_final;
    }

    public int getIs_produce() {
        return is_produce;
    }

    public void setIs_produce(int is_produce) {
        this.is_produce = is_produce;
    }

    public String getProduce_time() {
        return produce_time;
    }

    public void setProduce_time(String produce_time) {
        this.produce_time = produce_time;
    }

    public String getWarranty_time() {
        return warranty_time;
    }

    public void setWarranty_time(String warranty_time) {
        this.warranty_time = warranty_time;
    }

    public String getManu_code() {
        return manu_code;
    }

    public void setManu_code(String manu_code) {
        this.manu_code = manu_code;
    }

    public String getManu_name() {
        return manu_name;
    }

    public void setManu_name(String manu_name) {
        this.manu_name = manu_name;
    }

    public String getManu_address() {
        return manu_address;
    }

    public void setManu_address(String manu_address) {
        this.manu_address = manu_address;
    }

    public String getManu_zip() {
        return manu_zip;
    }

    public void setManu_zip(String manu_zip) {
        this.manu_zip = manu_zip;
    }

    public String getManu_contact() {
        return manu_contact;
    }

    public void setManu_contact(String manu_contact) {
        this.manu_contact = manu_contact;
    }

    public String getManu_phone() {
        return manu_phone;
    }

    public void setManu_phone(String manu_phone) {
        this.manu_phone = manu_phone;
    }

    public String getManu_email() {
        return manu_email;
    }

    public void setManu_email(String manu_email) {
        this.manu_email = manu_email;
    }

    public String getSup_code() {
        return sup_code;
    }

    public void setSup_code(String sup_code) {
        this.sup_code = sup_code;
    }

    public String getSup_name() {
        return sup_name;
    }

    public void setSup_name(String sup_name) {
        this.sup_name = sup_name;
    }

    public String getSup_zip() {
        return sup_zip;
    }

    public void setSup_zip(String sup_zip) {
        this.sup_zip = sup_zip;
    }

    public String getSup_contact() {
        return sup_contact;
    }

    public void setSup_contact(String sup_contact) {
        this.sup_contact = sup_contact;
    }

    public String getSup_phone() {
        return sup_phone;
    }

    public void setSup_phone(String sup_phone) {
        this.sup_phone = sup_phone;
    }

    public String getSup_email() {
        return sup_email;
    }

    public void setSup_email(String sup_email) {
        this.sup_email = sup_email;
    }

    public int getOperator() {
        return operator;
    }

    public void setOperator(int operator) {
        this.operator = operator;
    }

    public String getDevice_code() {
        return device_code;
    }

    public void setDevice_code(String device_code) {
        this.device_code = device_code;
    }
}
