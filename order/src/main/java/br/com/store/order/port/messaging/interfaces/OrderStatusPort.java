package br.com.store.order.port.messaging.interfaces;

import br.com.store.order.domain.OrderDomain;
import org.apache.kafka.streams.kstream.KStream;
import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.SubscribableChannel;

public interface OrderStatusPort {

    String ACCEPT = "RECEIVE_ORDER";
    String REFUSED = "CREATE_ORDER";
    String LOG_ERROR = "LOG_ERROR";

    @Input(ACCEPT)
    SubscribableChannel receive();

    @Input(REFUSED)
    KStream<String, OrderDomain> refused();

    @Output(LOG_ERROR)
    KStream<String, OrderDomain> log();


}
