package com.controller.admin;

import com.config.ResponseHelper;
import com.config.ResponseModel;
import com.entity.Order;
import com.enums.OrderAction;
import com.enums.OrderType;
import com.model.OrderModel;
import com.service.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author liugh
 * @since 2018-10-17
 */
@RestController
public class OrderController {

    @Autowired
    private IOrderService orderService;

    /**
     * 测试工作流
     * 订单发货(动作),待发货-->已发货(状态)
     * @param orderType
     * @param orderModel
     * @return
     * @throws Exception
     */
    @PutMapping(value = "/deliver/{orderType}")
    public ResponseModel<Order> updateDeliver(@PathVariable String orderType, @RequestBody OrderModel orderModel)
            throws Exception {
        Order orderDef = orderService.handleOrder(OrderAction.deliver, OrderType.getInstance(orderType), orderModel);
        return ResponseHelper.succeed(orderDef);
    }


}
