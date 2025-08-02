package com.example.station.service.impl;

import com.alibaba.fastjson.JSON;
import com.example.station.entity.User;
import com.example.station.mapper.UserMapper;
import com.example.station.menu.AreaLevelConst;
import com.example.station.service.UserService;
import com.example.station.utils.CommonResult;
import com.example.station.utils.JSONUtils;
import com.example.station.utils.JWTUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Resource
    private UserMapper userMapper;

    /**
     * 用户登陆
     * @param username
     * @param password
     * @return
     */
    @Override
    public CommonResult login(String username, String password) {
        User user = userMapper.getOneUser(username);
        if (user == null) {
            return CommonResult.fail("用户名不存在");
        }
        if (user.getState() == 1) {
            return CommonResult.fail("该用户已被冻结");
        }

        if (password.equals(user.getPassword())) {
            String token = JWTUtils.creatToken(user);
            return new CommonResult("登陆成功", 200, token);
        } else {
            return CommonResult.fail("密码错误");
        }
    }
    @Override
    public CommonResult loginSSO(String username) {
        User user = userMapper.getOneUser(username);
        if (user == null) {
            return CommonResult.fail("用户名不存在");
        }
        if (user.getState() == 1) {
            return CommonResult.fail("该用户已被冻结");
        }
        String token = JWTUtils.creatToken(user);

        return new CommonResult("登陆成功", 200, token);

    }
    /**
     * 用户注册
     * @param user
     * @return
     */
    @Override
    public CommonResult registration(User user) {
        if (userMapper.selectUsername(user.getUsername()) != 0) {
            return CommonResult.fail("该用户名已被注册");
        } else {
//            帐户状态
            user.setState(0);
//            账户级别
            user.setAuthority(1);
//            帐户地区权限，2为未授权
            user.setArea_level(1);
//            帐户地区编号，0为省级或未授权
            boolean success = userMapper.addUser(user) != 0;
            return success ? CommonResult.success("注册成功") : CommonResult.fail("注册失败");
        }
    }

    /**
     * 修改用户地区权限
     * @param id
     * @param authority
     * @param area_level
     * @param region_id
     * @return
     */
    @Override
    public CommonResult modifyAuthority(int id,String staff, int authority, int area_level, int region_id) {
        CommonResult successResult = CommonResult.success("授权成功");
        CommonResult failedResult = CommonResult.fail("授权失败");
        boolean success = false;
        if (area_level == AreaLevelConst.PROVINCE) {
            success = userMapper.updateUserAuthority(id,staff, authority, area_level, 0) != 0;
        } else {
            success = userMapper.updateUserAuthority(id,staff, authority, area_level, region_id) != 0;
        }
        return success ? successResult : failedResult;
    }

    /**
     * 修改用户状态
     * @param id
     * @param state
     * @return
     */
    @Override
    public CommonResult modifyState(int id, int state) {
        boolean success = userMapper.modifyUserState(id, state) != 0;
        return success ? CommonResult.success("修改用户状态成功") : CommonResult.fail("修改用户状态失败");
    }

    /**
     * 按页数获取用户列表
     * @param area_level
     * @param region_id
     * @param currentPage  当前页码
     * @return
     */
    @Override
    public String getUser(int area_level, int region_id, int currentPage) {
        int page;
        List<User> list;
        if (area_level == AreaLevelConst.PROVINCE) {
            int count = userMapper.getUserMount();
            page = calculatePage(count);
            list = userMapper.getUser((currentPage - 1) * 12);
        } else {
            int count = userMapper.getUserMountByRegion(region_id);
            page = calculatePage(count);
            list = userMapper.getUserByRegion((currentPage - 1) * 12, region_id);
        }
        return JSONUtils.toUserString("获取用户成功", 200, list, page);
    }

    /**
     * 获取全部用户列表
     * @param area_level
     * @param region_id
     * @return
     */
    @Override
    public CommonResult<List<User>> getUser(int area_level, int region_id) {
        List<User> list;
        if (area_level == AreaLevelConst.PROVINCE) {
            list = userMapper.getUserWithoutPage();
        } else {
            list = userMapper.getUserWithoutPageByRegion(region_id);
        }
        return CommonResult.success("获取用户成功", list);
    }

    @Override
    public CommonResult updatePassword(int id, String password) {
        boolean success = userMapper.updatePassword(id, password) != 0;
        return success ? CommonResult.success("修改密码成功") : CommonResult.fail("修改密码失败");
    }

    @Override
    public CommonResult updateStaff(int id,String staff) {
        boolean success = userMapper.updateStaff(id, staff) != 0;
        return success ? CommonResult.success("修改姓名成功") : CommonResult.fail("修改姓名失败");
    }

    @Override
    public CommonResult modifyPassword(int id, String password){
        boolean success = userMapper.updatePassword(id, password) != 0;
        return success ? CommonResult.success("修改密码成功") : CommonResult.fail("修改密码失败");
    }

    /**
     * 获取用户信息
     * @param id  用户编号
     * @return
     */
    @Override
    public String getPersonalInformation(int id) {
        User user = userMapper.getPersonalInformation(id);
        return JSON.toJSONString(user);
    }


    protected int calculatePage(int amount){
        int page = 0;
        if(amount % 12 == 0){
            page = amount / 12;
        }else{
            page = amount / 12 + 1;
        }
        return page;
    }
}
