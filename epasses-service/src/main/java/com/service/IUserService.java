package com.service;


import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import com.entity.User;

import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author liugh123
 * @since 2018-05-03
 */
public interface IUserService extends IService<User> {

    /**
     * 根据用户名查询用户
     * @param username 用户名
     * @return 用户
     */
    User getUserByUserName(String username);

    User getUserByMobile(String mobile);

    /**
     * 注册用户
     * @param user
     * @param roleCode
     * @return
     */
    User register(User user, String roleCode);

    Map<String, Object> getLoginUserAndMenuInfo(User user);

    void deleteByUserNo(String userNo)throws Exception;

    Page<User> selectPageByConditionUser(Page<User> userPage, String info, Integer[] status, String startTime, String endTime);

    /**
     * 登录-校验用户的账号和密码
     * @param requestJson 请求参数
     * @return 结果集
     * @throws Exception 抛出异常
     */
    Map<String,Object> checkMobileAndPasswd(JSONObject requestJson)throws Exception;

    Map<String,Object> checkMobileAndCatcha(JSONObject requestJson)throws Exception;

    User checkAndRegisterUser(JSONObject requestJson)throws Exception;

    User updateForgetPasswd(JSONObject requestJson)throws Exception;

    void resetMobile(User currentUser, JSONObject requestJson)throws Exception;

    void resetPassWord(User currentUser, JSONObject requestJson)throws Exception;

    User insertUserByAdmin(JSONObject requestJson)throws Exception;
}
