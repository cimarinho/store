package br.com.store.order.port.messaging;

import br.com.store.order.port.messaging.interfaces.OrderStatusPort;
import br.com.store.order.port.request.OrderRequest;
import br.com.store.order.port.response.OrderResponse;
import br.com.store.order.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;

@Slf4j
@EnableBinding({OrderStatusPort.class})
public class OrderStatusAcceptPortImpl {

    @Autowired
    private OrderService orderService;

    @StreamListener(OrderStatusPort.ACCEPT)
    public void accept(OrderRequest orderRequest) {


    }

}
