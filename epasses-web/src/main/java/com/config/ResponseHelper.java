package com.config;



import base.CodeEnum;

import java.io.Serializable;

/**
 * 统一返回相应参数
 * @author liugh 53182347@qq.com
 */
public class ResponseHelper<T> implements Serializable {


    public static <T> ResponseModel<T> succeed(T model, String msg) {
        return succeed(model, CodeEnum.SUCCESS.getCode(), msg);
    }

    public static <T> ResponseModel<T> succeed(T model) {
        return succeed(model, CodeEnum.SUCCESS.getCode(), CodeEnum.SUCCESS.getMsg());
    }

    public static <T> ResponseModel<T> succeed(T datas, String code, String msg) {
        return new ResponseModel<T>(datas, code, msg);
    }

    public static <T> ResponseModel<T> failed2Message(String msg) {
        return failedWith(null, CodeEnum.ERROR.getCode(), msg);
    }

    public static <T> ResponseModel<T> failed(T model, String msg) {
        return failedWith(model, CodeEnum.ERROR.getCode(), msg);
    }
    public static <T> ResponseModel<T> failed(T model) {
        return failedWith(model, CodeEnum.ERROR.getCode(), CodeEnum.ERROR.getMsg());
    }


    public static <T> ResponseModel<T> failedWith(T datas, String code, String msg) {
        return new ResponseModel<>(datas, code, msg);
    }

}
