package com.example.station.service;

import com.example.station.entity.User;
import com.example.station.utils.CommonResult;

import java.util.List;

public interface UserService {
    CommonResult login(String username, String password);

    CommonResult loginSSO(String username);

    CommonResult registration(User user);

    CommonResult modifyAuthority(int id, String staff,int authority, int area_level, int region_id);

    CommonResult modifyState(int id, int state);

    CommonResult modifyPassword(int id, String password);

    String getUser(int area_level, int region_id, int currentPage);

    CommonResult<List<User>> getUser(int area_level, int region_id);

    CommonResult updatePassword(int id, String password);

    CommonResult updateStaff(int id, String staff);

    String getPersonalInformation(int id);
}
