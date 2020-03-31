package br.com.store.order.application.messaging.interfaces;

import br.com.store.order.application.request.OrderRequest;
import br.com.store.order.application.request.PaymentRequest;
import org.apache.kafka.streams.kstream.KStream;
import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

public interface Order {

    String RECEIVE = "RECEIVE_ORDER";
    String CREATE = "CREATE_ORDER";

    String REFUSED_PAYMENT = "REFUSED_PAYMENT";
    String COMPLETE_PAYMNENT = "COMPLETE_ORDER";

    String REFUSED_SHIPPING = "REFUSED_SHIPPING";
    String COMPLETE_SHIPPING = "COMPLETE_SHIPPING";

    String ORDER_LOG = "ORDER_LOG";


    @Input(RECEIVE)
    SubscribableChannel receive();

    @Output(CREATE)
    MessageChannel create();

    @Input(REFUSED_PAYMENT)
    KStream<String, OrderRequest> refusedPayment();

    @Input(COMPLETE_PAYMNENT)
    SubscribableChannel completePayment();

    @Input(REFUSED_SHIPPING)
    KStream<String, PaymentRequest> refusedShipping();

    @Input(COMPLETE_SHIPPING)
    SubscribableChannel completeShipping();


    @Output(ORDER_LOG)
    KStream<String, OrderRequest> orderLog();



}

