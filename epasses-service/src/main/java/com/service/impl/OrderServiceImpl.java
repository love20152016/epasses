package com.service.impl;


import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.entity.Order;
import com.enums.OrderAction;
import com.enums.OrderType;
import com.mapper.OrderMapper;
import com.model.OrderModel;
import com.service.IOrderService;
import com.service.handler.OrderHandler;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 订单主表，当前表只保存流转中的订单信息 服务实现类
 * </p>
 *
 * @author liugh
 * @since 2018-10-17
 */
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements IOrderService {


    @Override
    public Order handleOrder(OrderAction action, OrderType orderType, OrderModel orderDef) throws Exception {
        Order order = OrderHandler.handle(action, orderType, orderDef);
        return order;
    }

}
