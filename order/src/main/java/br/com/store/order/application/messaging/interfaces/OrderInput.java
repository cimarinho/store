package br.com.store.order.application.messaging.interfaces;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

public interface OrderInput {

    String RECEIVE = "receive";
    String CREATE = "create";
    String SAVELOG = "saveLog";

    @Input(RECEIVE)
    SubscribableChannel receive();

    @Output(CREATE)
    MessageChannel create();

    @Input(SAVELOG)
    SubscribableChannel saveLog();










}

