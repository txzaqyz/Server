package com.example.station.controller;

import com.example.station.annotation.CheckOperationAuthority;
import com.example.station.entity.User;
import com.example.station.service.impl.UserServiceImpl;
import com.example.station.utils.CommonResult;
import com.example.station.utils.JWTUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
public class UserController {
    @Resource
    private UserServiceImpl userService;

    private static final int ADMIN_ID = 1;
    private static final int MODIFY_PERMISSION = 0;
    /**
     * 用户登陆
     * @param username 用户名
     * @param password 密码
     * @return
     */
    @PostMapping("/login")
    public CommonResult login(@RequestParam(value = "username",required = true) String username,
                              @RequestParam(value = "password", required = true) String password) {
        return userService.login(username, password);
    }
    @PostMapping("/loginSSO")
    public CommonResult loginSSO(@RequestParam(value = "username",required = true) String username) {
        return userService.loginSSO(username);
    }
    /**
     * 用户注册
     * @param user 用户信息，前端以json形式传给后端
     * @return
     */
    @PostMapping("/register")
    public CommonResult register(@RequestBody User user) {
        return userService.registration(user);
    }

    /**
     * 管理员授权用户
     * @param request
     * @param id
     * @param authority
     * @param areaLevel
     * @param regionId
     * @return
     */
    @PostMapping("/user/modifyAuthority")
    public CommonResult modifyAuthority(HttpServletRequest request,
                                  @RequestParam(value = "id", required = true) int id,
                                  @RequestParam(value = "staff", required = true) String staff,
                                  @RequestParam(value = "authority", required = true) int authority,
                                  @RequestParam(value = "areaLevel", required = true) int areaLevel,
                                  @RequestParam(value = "regionId", required = true) int regionId) {
        String token = JWTUtils.parseToken(request);
        int permissions = JWTUtils.verifyAuthority(token);
        if (id == ADMIN_ID) {
            return new CommonResult("不能修改该用户权限", 401);
        }

        if (permissions != MODIFY_PERMISSION) {
            return CommonResult.noPermission();
        } else {
            return userService.modifyAuthority(id,staff,authority, areaLevel, regionId);
        }
    }

    /**
     * 管理员修改用户使用状态，正常/冻结
     * @param request
     * @param id
     * @param state
     * @return
     */
    @PostMapping("/user/modifyState")
    public CommonResult modifyState(HttpServletRequest request,
                              @RequestParam(value = "id",required = true)int id,
                              @RequestParam(value = "state",required = true)int state) {
        String token = JWTUtils.parseToken(request);
        int permissions = JWTUtils.verifyAuthority(token);
        if (id == ADMIN_ID) {
            return new CommonResult("不能修改该用户状态", 401);
        }

        if (permissions != MODIFY_PERMISSION) {
            return CommonResult.noPermission();
        } else {
            return userService.modifyState(id, state);
        }
    }
    /**
     * 管理员修改用户密码
     * @param request
     * @param id
     * @param password
     * @return
     */
    @PostMapping("/user/modifyPassword")
    public CommonResult modifyPassword(HttpServletRequest request,
                                    @RequestParam(value = "id",required = true)int id,
                                    @RequestParam(value = "password",required = true)String password) {
        String token = JWTUtils.parseToken(request);
        int permissions = JWTUtils.verifyAuthority(token);
        if (id == ADMIN_ID) {
            return new CommonResult("不能修改该用户密码", 401);
        }

        if (permissions != MODIFY_PERMISSION) {
            return CommonResult.noPermission();
        } else {
            return userService.modifyPassword(id, password);
        }
    }

    /**
     * 获取用户,按页显示
     * @param request
     * @param currentPage  当前页码从1开始，每页12条数据
     * @return
     */
    @CheckOperationAuthority
    @PostMapping("/user/getUser")
    public String getUser(HttpServletRequest request,
                          @RequestParam(value = "currentPage",required = true)int currentPage) {
        String token = JWTUtils.parseToken(request);
        int areaLevel = JWTUtils.parseAreaLevel(token);
        int regionId = JWTUtils.parseRegionId(token);
        return userService.getUser(areaLevel, regionId, currentPage);
    }

    /**
     * 获取全部用户
     * @param request
     * @return
     */
    @CheckOperationAuthority
    @PostMapping("/user/getAllUser")
    public CommonResult<List<User>> getAllUser(HttpServletRequest request) {
        String token = JWTUtils.parseToken(request);
        int areaLevel = JWTUtils.parseAreaLevel(token);
        int regionId = JWTUtils.parseRegionId(token);
        return userService.getUser(areaLevel, regionId);
    }

    /**
     *更新个人信息
     * @param request
     * @param type   修改信息类型，0密码，1职工姓名
     * @param password  密码
     * @param staff   员工姓名
     * @return
     */
    @PostMapping("/user/updateUserInformation")
    public CommonResult updateUserInformation(HttpServletRequest request,
                                        @RequestParam(value = "type", required = true) int type,
                                        @RequestParam(value = "password", required = false) String password,
                                        @RequestParam(value = "staff", required = false) String staff) {
        int id = JWTUtils.parseId(JWTUtils.parseToken(request));
        if (type == 0) {
            return userService.updatePassword(id, password);
        } else {
            return userService.updateStaff(id, staff);
        }
    }

    /**
     * 获取用户个人信息（含权限）
     * @param request
     * @return
     */
    @PostMapping("/user/getPersonalInformation")
    public String getPersonalInformation(HttpServletRequest request) {
        int id = JWTUtils.parseId(JWTUtils.parseToken(request));
        return userService.getPersonalInformation(id);
    }
}
