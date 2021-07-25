package com.service.impl;


import base.BusinessException;
import base.CodeEnum;
import base.SmsSendResponse;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.entity.SmsVerify;
import com.mapper.SmsVerifyMapper;
import com.service.ISmsVerifyService;
import org.springframework.stereotype.Service;
import util.ComUtil;
import util.GenerationSequenceUtil;
import util.SmsSendUtil;
import util.StringUtil;

import java.util.List;

/**
 * <p>
 * 验证码发送记录 服务实现类
 * </p>
 *
 * @author liugh123
 * @since 2018-06-25
 */
@Service
public class SmsVerifyServiceImpl extends ServiceImpl<SmsVerifyMapper, SmsVerify> implements ISmsVerifyService {

    @Override
    public List<SmsVerify> getByMobileAndCaptchaAndType(String mobile, String captcha, int type) {
        EntityWrapper<SmsVerify> smsQuery = new EntityWrapper<>();
        smsQuery.where("mobile={0} and sms_verify={1} and  sms_type = {2}",
                mobile,captcha,type);
        smsQuery.orderBy("create_time",false);
        return this.selectList(smsQuery);
    }

    @Override
    public SmsVerify addAndGetMobileAndCaptcha(String smsType, String mobile) throws Exception {
        if(!StringUtil.checkMobileNumber(mobile)){
            throw new BusinessException(CodeEnum.MOBILE_ERROR.getMsg(),CodeEnum.MOBILE_ERROR.getCode());
        }
        String randNum = GenerationSequenceUtil.getRandNum(4);
        SmsSendResponse smsSendResponse = SmsSendUtil.sendMessage(mobile,
                "校验码: " + randNum+"。您正在进行"+SmsSendUtil.SMSType.getSMSType(smsType).toString()+"的操作,请在5分钟内完成验证，注意保密哦！");
        SmsVerify smsVerify =SmsVerify.builder().smsId(smsSendResponse.getMsgId()).mobile(mobile).smsVerify(randNum)
                .smsType(SmsSendUtil.SMSType.getType(smsType)).createTime(System.currentTimeMillis()).build();
        this.insert(smsVerify);
        return smsVerify;
    }

    @Override
    public void captchaCheck(String mobile,String smsType, String captcha) throws Exception {
        if(!StringUtil.checkMobileNumber(mobile)){
            throw new BusinessException(CodeEnum.MOBILE_ERROR.getMsg(),CodeEnum.MOBILE_ERROR.getCode());
        }
        List<SmsVerify> smsVerifies = this.getByMobileAndCaptchaAndType(mobile,
                captcha,SmsSendUtil.SMSType.getType(smsType));
        if(ComUtil.isEmpty(smsVerifies)){
            throw new BusinessException(CodeEnum.VERIFY_PARAM_ERROR.getMsg(),CodeEnum.VERIFY_PARAM_ERROR.getCode());
        }
        if(SmsSendUtil.isCaptchaPassTime(smsVerifies.get(0).getCreateTime())){
            throw new BusinessException(CodeEnum.VERIFY_PARAM_PASS.getMsg(),CodeEnum.VERIFY_PARAM_PASS.getCode());
        }
    }
}
