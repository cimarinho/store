package br.com.store.order.port.messaging;

import br.com.store.order.port.messaging.interfaces.OrderPort;
import br.com.store.order.port.request.OrderRequest;
import br.com.store.order.port.response.OrderResponse;
import br.com.store.order.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.SendTo;

@Slf4j
@EnableBinding({OrderPort.class})
public class OrderPortImpl {

    @Autowired
    private OrderService orderService;

    @StreamListener(OrderPort.RECEIVE)
    @SendTo(OrderPort.CREATE)
    public OrderResponse receive(OrderRequest orderRequest) {
        log.debug("receive ",orderRequest);
        OrderResponse order = orderService.createOrder(orderRequest);
        return order;
    }

}