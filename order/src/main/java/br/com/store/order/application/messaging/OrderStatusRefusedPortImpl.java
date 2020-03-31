package br.com.store.order.application.messaging;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.streams.kstream.KStream;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.SendTo;

//@Slf4j
//@EnableBinding({OrderStatus.class})
public class OrderStatusRefusedPortImpl {
//
//    @StreamListener(OrderStatus.REFUSED)
//    @SendTo(OrderStatus.LOG_ERROR)
//    public KStream<String, OrderStatus> process(KStream<String, OrderStatus> order) {
//
//        order.peek((k,v)->{
//           log.error("error" +v);
//        });
//
//
//        return order;
//    }

}
