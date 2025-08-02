package com.example.station.utils;

import com.example.station.entity.Menu;
import com.example.station.entity.SubMenu;
import com.example.station.menu.MenuConst;
import com.example.station.menu.SubMenuConst;

import java.util.ArrayList;
import java.util.List;

/**
 * 生成菜单的工具类
 */
public class MenuUtils {

    /**
     * 创建权限0或1的下拉菜单
     * @return
     */
    public static List<Menu> creatMenu1() {
        List<Menu> list = creatMenu2();
        MenuConst[] menuConst = MenuConst.values();
        for (int i = 0; i < menuConst.length - 1; i++) {
            Menu menu = new Menu();
            menu.setId(menuConst[i].getId());
            menu.setMenuName(menuConst[i].getMenuName());
            menu.setPath(menuConst[i].getPath());
            menu.setIcon(menuConst[i].getIcon());
            list.add(menu);
        }
        return list;
    }

    /**
     * 创建权限2的下拉菜单
     * @return
     */
    public static List<Menu> creatMenu2(){
        List<Menu> list = new ArrayList<>();
//        List<SubMenu> subList = new ArrayList<>();
        MenuConst menuConst = MenuConst.INFORMATION_MONITORING;
//        SubMenuConst[] subMenuConst = SubMenuConst.values();
        Menu menu = new Menu();
        menu.setId(menuConst.getId());
        menu.setMenuName(menuConst.getMenuName());
        menu.setPath(menuConst.getPath());
        menu.setIcon(menuConst.getIcon());
//        for (SubMenuConst sub : subMenuConst) {
//            SubMenu subMenu = new SubMenu();
//            subMenu.setId(sub.getId());
//            subMenu.setMenuName(sub.getMenuName());
//            subMenu.setPath(sub.getPath());
//            subList.add(subMenu);
//        }
//        menu.setSubMenu(subList);
        list.add(menu);
        return list;
    }
}
