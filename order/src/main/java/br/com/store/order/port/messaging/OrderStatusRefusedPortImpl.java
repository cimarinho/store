package br.com.store.order.port.messaging;

import br.com.store.order.port.messaging.interfaces.OrderStatusPort;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.streams.kstream.KStream;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.SendTo;

@Slf4j
@EnableBinding({OrderStatusPort.class})
public class OrderStatusRefusedPortImpl {

    @StreamListener(OrderStatusPort.REFUSED)
    @SendTo(OrderStatusPort.LOG_ERROR)
    public KStream<String, OrderStatusPort> process(KStream<String, OrderStatusPort> order) {

        order.peek((k,v)->{
           log.error("error" +v);
        });


        return order;
    }

}
