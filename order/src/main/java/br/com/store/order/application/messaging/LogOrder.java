package br.com.store.order.application.messaging;

import br.com.store.order.application.messaging.interfaces.OrderInput;
import br.com.store.order.application.request.OrderRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.handler.annotation.Headers;

@Slf4j
@EnableBinding({OrderInput.class})
public class LogOrder {


    @StreamListener(OrderInput.SAVE_LOG)
    public void completePayment(OrderRequest orderRequest, @Headers MessageHeaders headers) {
        log.info("ORDER_LOG = correlation_id["+headers.get("correlation_id")+"");

    }
}
