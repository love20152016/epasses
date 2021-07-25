package com.service;

import com.baomidou.mybatisplus.service.IService;
import com.entity.User;
import com.entity.UserThirdparty;
import com.model.ThirdPartyUser;

/**
 * <p>
 * 第三方用户表 服务类
 * </p>
 *
 * @author liugh123
 * @since 2018-07-27
 */
public interface IUserThirdpartyService extends IService<UserThirdparty> {

    User insertThirdPartyUser(ThirdPartyUser param, String password)throws Exception;

}
