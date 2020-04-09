package br.com.store.order.application.controller;


import br.com.store.order.application.messaging.interfaces.OrderInput;
import br.com.store.order.application.request.OrderItemRequest;
import br.com.store.order.application.request.OrderRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;

@SpringBootApplication
@EnableBinding({OrderInput.class})
@RestController
public class OrderRequetController {

    @Autowired
    @Qualifier(value = "receive")
    MessageChannel messageChannel;

    @GetMapping
    public String order() throws Exception {
        OrderRequest r = new OrderRequest();
        r.setIdClient("2121");
        r.setIdOrder(5);
        r.setOrderDate("2020-04-01");
        r.setTotalPrice(55.56);
        OrderItemRequest i = new OrderItemRequest();
        i.setPrice(4343.4);
        i.setProductName("PRODUTO");
        r.setItems(Arrays.asList(i));

        messageChannel.send(MessageBuilder.withPayload(r).setHeader("correlation_id","123").build());


        return "OK";
    }
}