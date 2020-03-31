package br.com.store.order.application.messaging;

import br.com.store.order.application.messaging.interfaces.Order;
import br.com.store.order.application.request.OrderRequest;
import br.com.store.order.application.request.PaymentRequest;
import br.com.store.order.domain.OrderServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.streams.kstream.KStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.SendTo;


@Slf4j
@EnableBinding({Order.class})
public class OrderPayment {

    @Autowired
    private OrderServiceImpl orderService;

    @StreamListener(Order.COMPLETE_PAYMNENT)
    public void completePayment(PaymentRequest orderRequest) {
        log.info("completePayment "+orderRequest);

        log.info("save");

    }

    @StreamListener(Order.REFUSED_PAYMENT)
    @SendTo(Order.ORDER_LOG)
    public KStream<String, OrderRequest> refusedPayment(KStream<String, PaymentRequest> paymentRequest){
        log.info("refusedPayment ");
        paymentRequest.peek((k,v) -> System.out.println(v));
        KStream<String, OrderRequest> k = paymentRequest.mapValues(PaymentRequest::getOrder);
        k.peek((key,value) ->{
            System.out.println("OrderRequest");
            System.out.println(value);
        });
        return k;
    }
}
