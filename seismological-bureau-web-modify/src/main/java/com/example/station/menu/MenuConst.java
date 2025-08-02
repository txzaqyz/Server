package com.example.station.menu;

public enum MenuConst {
    USER_MANAGER(0, "用户管理", "/center/users", "el-icon-user"),
    EQUIPMENT_MANAGER(1, "设备管理", "/center/device", "el-icon-setting"),
    WORKSHEET_MANAGER(2, "工单管理", "/center/work_order", "el-icon-document"),
//    LEYUAN_TEST(4, "数据获取", "/center/test", "el-icon-s-data"),
    MANUFACTURER_MANAGER(3,"厂商管理","/center/manufacturer","el-icon-box"),
    SUPPLIER_MANAGER(4,"供应商管理","/center/supplier","el-icon-shopping-cart-1"),
    AllEquipment(5,"设备监控","/center/allEquipment","el-icon-s-grid"),
    INFORMATION_MONITORING(6, "监控信息", "/center/js", "el-icon-monitor");




    private int id;
    private String menuName;
    private String path;
    private String icon;



    MenuConst(int id, String menuName, String path, String icon) {
        this.id = id;
        this.menuName = menuName;
        this.path = path;
        this.icon = icon;
    }

    public int getId() {
        return id;
    }

    public String getMenuName() {
        return menuName;
    }

    public String getPath() {
        return path;
    }

    public String getIcon() {
        return icon;
    }

    @Override
    public String toString() {
        return "{" +
                "id:" + id +
                ", menuName:'" + menuName + '\'' +
                ", path:'" + path + '\'' +
                ", icon:'" + icon + '\'' +
                '}';
    }
}
