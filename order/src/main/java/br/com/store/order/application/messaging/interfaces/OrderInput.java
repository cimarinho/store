package br.com.store.order.application.messaging.interfaces;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

public interface OrderInput {

    String RECEIVE = "receive";
    String CREATE = "create";
    String UPDATE = "update";
    String ERROR_PAYMENT = "errorPayment";

    @Input(RECEIVE)
    SubscribableChannel receive();

    @Output(CREATE)
    MessageChannel create();

    @Input(UPDATE)
    SubscribableChannel update();

    @Input(ERROR_PAYMENT)
    SubscribableChannel errorPayment();

}

