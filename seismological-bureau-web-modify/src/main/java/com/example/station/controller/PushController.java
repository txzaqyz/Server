package com.example.station.controller;
import com.cims.sync.constant.JsonResult;
import com.cims.sync.dto.AccountBean;
import com.cims.sync.dto.ApiPushDTO;
import com.cims.sync.exception.BusinessException;
import com.cims.sync.exception.ErrorCode;
import com.cims.sync.util.MD5Util;
import com.cims.sync.util.SecurityUtil;
import com.example.station.entity.User;
import com.cims.sync.util.SignBuilder;
import com.example.station.config.SSOClientConfig;
import com.example.station.mapper.UserMapper;
import com.example.station.utils.JsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.annotation.Resource;
import java.util.List;

/**
 * @Classname UserController
 * @Description None
 * @Date 2019/6/25 17:51
 * @author wd
 */
@Controller
@RequestMapping("/push")
public class PushController {

    @Resource
    private UserMapper userMapper;

    @Autowired
    private SSOClientConfig ssoClientConfig;
    //用来添加新用户


//    @Autowired
//    private DeptInfoDao deptInfoDao;
//
//    @Autowired
//    private AccountInfoDao accountInfoDao;

    @RequestMapping("/org")
    @ResponseBody
    public Object dept(@RequestBody ApiPushDTO apiPushDTO) {

        System.out.println("部门推送原始数据："+ JsonUtil.obj2json(apiPushDTO));
        String newSecret = MD5Util.getMD5String(ssoClientConfig.getAppSecret());
        //检验签名
        String sign = SignBuilder.create(ssoClientConfig.getAppId(),newSecret)
                .requestPath("/push/org")
                .requestMethod(HttpMethod.POST.name())
                .salt(apiPushDTO.getSalt())
                .timestamp(apiPushDTO.getTimestamp())
                .sign();
        if (!sign.equals(apiPushDTO.getSign())) {
            return JsonResult.err(ErrorCode.SIGN_CHECK_ERROR);
        }

        String data = SecurityUtil.decryptAES(apiPushDTO.getSyncData(), newSecret);
        System.out.println(data);
//        List<DeptBean> deptBeans = JsonUtil.json2list(data, DeptBean.class);
//
//        try {
//            deptBeans.forEach(deptBean -> {
//                log.info("收到部门数据："+JsonUtil.obj2json(deptBean));
//                if (DeptBean.MOD_STATUS.equals(deptBean.getStatus())) {
//                    DeptInfo deptInfo = deptInfoDao.findByDeptNum(deptBean.getDepNum());
//                    if (deptInfo == null) {
//                        deptInfo = new DeptInfo();
//                        deptInfo.setDeptNum(deptBean.getDepNum());
//                        deptInfo.setCreateTime(new Date());
//                    }
//                    if (StringUtils.isNotBlank(deptBean.getParentNum())) {
//                        DeptInfo parent = deptInfoDao.findByDeptNum(deptBean.getParentNum());
//                        if (parent == null) {
//                            throw new BusinessException(9001, "上级部门不存在:parentNum="+deptBean.getParentNum());
//                        }
//                    }
//                    deptInfo.setParentNum(deptBean.getParentNum());
//                    deptInfo.setAttrs(JsonUtil.obj2json(deptBean.getAttrs()));
//                    deptInfo.setUpdateTime(new Date());
//                    deptInfoDao.save(deptInfo);
//                    log.info("保存部门数据成功："+JsonUtil.obj2json(deptInfo));
//                } else if (DeptBean.DEL_STATUS.equals(deptBean.getStatus())) {
//                    DeptInfo deptInfo = deptInfoDao.findByDeptNum(deptBean.getDepNum());
//                    if (deptInfo != null) {
//                        deptInfoDao.delete(deptInfo);
//                        log.info("删除部门数据成功："+JsonUtil.obj2json(deptInfo));
//                    } else {
//                        log.info("删除部门数据失败：数据不存在");
//                    }
//                }
//            });
//        } catch (BusinessException e) {
//            e.printStackTrace();
//            return JsonResult.err(e.getErrCode(), e.getErrMsg());
//        } catch (Exception e) {
//            e.printStackTrace();
//            return JsonResult.err(9001, e.getMessage());
//        }
//        return JsonResult.ok(apiPushDTO.getSign());

        return JsonResult.ok(apiPushDTO.getSign());
    }
    @RequestMapping(value = "/account")
    @ResponseBody
    public Object account(@RequestBody ApiPushDTO apiPushDTO) {


        System.out.println("账号推送原始数据："+ JsonUtil.obj2json(apiPushDTO));
        String newSecret = MD5Util.getMD5String(ssoClientConfig.getAppSecret());
        //检验签名
        String sign = SignBuilder.create(ssoClientConfig.getAppId(),newSecret)
                .requestPath("/push/account")
                .requestMethod(HttpMethod.POST.name())
                .salt(apiPushDTO.getSalt())
                .timestamp(apiPushDTO.getTimestamp())
                .sign();
        if (!sign.equals(apiPushDTO.getSign())) {
            return JsonResult.err(ErrorCode.SIGN_CHECK_ERROR);
        }
        String data = SecurityUtil.decryptAES(apiPushDTO.getSyncData(), newSecret);
        System.out.println(data);

    //接受到的数据进行处理
        List<AccountBean> accountBeans = JsonUtil.json2list(data, AccountBean.class);
        try {
            accountBeans.forEach(accountBean -> {
                System.out.println("收到账号数据："+JsonUtil.obj2json(accountBean));
                User user=userMapper.getOneUser(accountBean.getPrincipalId());
                if (AccountBean.NORMAL_STATUS.equals(accountBean.getStatus())) {


                   //如果是新用户，创建一个新的账户
                    if (user == null) {
                        user=new User();
                        user.setUsername(accountBean.getPrincipalId());
                        user.setStaff(accountBean.getAttributes().get("realName"));
                        user.setArea_level(1);
                        user.setAuthority(1);
                        user.setPassword("123456");
                        user.setRegion_id(1);
                        user.setState(0);
                        userMapper.addUser(user);
                    }else{
                        user.setUsername(accountBean.getPrincipalId());
                        user.setStaff(accountBean.getAttributes().get("realName"));
                        userMapper.updateStaffbyusername(user.getUsername(),user.getStaff());
                    }
                } else if (AccountBean.DELETE_STATUS.equals(accountBean.getStatus())
                        || AccountBean.DISABLE_STATUS.equals(accountBean.getStatus())) {
                    if (user != null) {
                        userMapper.deleteUser(user.getUsername());
                        System.out.println("删除账号数据成功："+JsonUtil.obj2json(user));
                    } else {
                        System.out.println("删除账号数据失败：数据不存在");
                    }
                }
            });
        } catch (BusinessException e) {
            e.printStackTrace();
            return JsonResult.err(e.getErrCode(), e.getErrMsg());
        } catch (Exception e) {
            e.printStackTrace();
            return JsonResult.err(9001, e.getMessage());
        }
    return JsonResult.ok(apiPushDTO.getSign());
    }
}
