package br.com.store.order.application.messaging.interfaces;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

public interface OrderInput {

    String RECEIVE = "receive_order";
    String CREATE = "create_order";
    String UPDATE = "update_order";
    String ERROR_PAYMENT = "error_payment";

    @Input(RECEIVE)
    SubscribableChannel receive();

    @Output(CREATE)
    MessageChannel create();

    @Input(UPDATE)
    SubscribableChannel update();

    @Input(ERROR_PAYMENT)
    SubscribableChannel errorPayment();

}

