package br.com.store.order.application.messaging.interfaces;

import br.com.store.order.application.request.OrderRequest;
import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

public interface OrderInput {

    String RECEIVE = "receive_order";
    String CREATE = "create_order";
    String SAVE_LOG = "order_log";

    @Input(RECEIVE)
    SubscribableChannel receive();

    @Output(CREATE)
    MessageChannel create();

    @Input(SAVE_LOG)
    SubscribableChannel saveLog();










}

