package com.example.station.menu;

public enum SubMenuConst {
    PROVINCE_SUBMENU(0, "省级信息", "/center/js");
//    CITY_SUBMENU(1, "市级信息", "/center/jstz/yc"),
//    AREA_SUBMENU(2, "区级信息", "/center/Detail/yc");

    private int id;
    private String menuName;
    private String path;

    SubMenuConst(int id, String menuName, String path) {
        this.id = id;
        this.menuName = menuName;
        this.path = path;
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

    @Override
    public String toString() {
        return "{" +
                "id:" + id +
                ", menuName:'" + menuName + '\'' +
                ", path:'" + path + '\'' +
                '}';
    }
}
