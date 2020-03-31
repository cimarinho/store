package br.com.store.order.application.messaging;

import br.com.store.order.application.messaging.interfaces.Order;
import br.com.store.order.application.request.OrderRequest;
import br.com.store.order.application.response.OrderResponse;
import br.com.store.order.domain.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.SendTo;

@Slf4j
@EnableBinding({Order.class})
public class OrderCreate {

    @Autowired
    private OrderService orderService;

    @StreamListener(Order.RECEIVE)
    @SendTo(Order.CREATE)
    public OrderResponse receive(OrderRequest orderRequest) {
        log.info("receive "+orderRequest);
        OrderResponse order = orderService.createOrder(orderRequest);
        return order;
    }

}