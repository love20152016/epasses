package com.service.handler;

import com.entity.Order;
import com.enums.OrderAction;
import com.enums.OrderType;
import com.model.OrderModel;
import com.service.processor.ActionProcessor;
import org.springframework.stereotype.Component;

/**
 * @author liugh
 * @since 2018-10-17
 */
@Component("ProductOrderHandler")
public class ProductOrderHandler extends OrderHandler {

	@Override
	public Order handleInternal(OrderAction action, OrderType orderType, OrderModel orderDef, Order currentOrder) throws Exception {
		return  ActionProcessor.process(action,orderType,orderDef,currentOrder);
	}
}
