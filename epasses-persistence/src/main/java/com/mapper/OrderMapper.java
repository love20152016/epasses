package com.mapper;


import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.entity.Order;

/**
 * <p>
 * 订单主表，当前表只保存流转中的订单信息 Mapper 接口
 * </p>
 *
 * @author liugh123
 * @since 2018-10-17
 */
public interface OrderMapper extends BaseMapper<Order> {

}
