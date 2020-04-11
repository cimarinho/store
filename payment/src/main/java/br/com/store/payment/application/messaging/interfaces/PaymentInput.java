package br.com.store.payment.application.messaging.interfaces;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

public interface PaymentInput {

    String RECEIVE = "receive";
    String CREATE = "create";

    @Input(RECEIVE)
    SubscribableChannel receive();

    @Output(CREATE)
    MessageChannel create();











}

