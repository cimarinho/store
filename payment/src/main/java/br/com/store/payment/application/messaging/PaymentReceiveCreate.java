package br.com.store.payment.application.messaging;

import br.com.store.order.application.avro.OrderInputAvro;
import br.com.store.payment.application.messaging.interfaces.PaymentInput;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.SendTo;

@Slf4j
@EnableBinding({PaymentInput.class})
public class PaymentReceiveCreate {

    @StreamListener(PaymentInput.RECEIVE)
    @SendTo(PaymentInput.CREATE)
    public Message<OrderInputAvro> receive(Message<OrderInputAvro> orderRequest, @Headers MessageHeaders headers) {
        log.info("receive " + orderRequest+ "correlation_id["+headers.get("correlation_id")+"");
        orderRequest.getPayload().setStatus("APPROVED_PAYMENT");
        return orderRequest;
    }



}