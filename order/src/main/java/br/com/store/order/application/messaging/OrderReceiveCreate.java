package br.com.store.order.application.messaging;

import br.com.store.order.application.messaging.interfaces.OrderInput;
import br.com.store.order.application.request.OrderRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.SendTo;

@Slf4j
@EnableBinding({OrderInput.class})
public class OrderReceiveCreate {


    @StreamListener(OrderInput.RECEIVE)
    @SendTo(OrderInput.CREATE)
    public Message<OrderRequest> receive(Message<OrderRequest> orderRequest, @Headers MessageHeaders headers) {
        log.info("receive " + orderRequest + "correlation_id[" + headers.get("correlation_id") + "");
        orderRequest.getPayload().setIdClient("11");
        return orderRequest;
    }

}