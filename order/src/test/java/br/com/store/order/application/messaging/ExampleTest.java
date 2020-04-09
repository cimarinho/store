package br.com.store.order.application.messaging;

import br.com.store.order.application.messaging.interfaces.OrderInput;
import br.com.store.order.application.request.OrderItemRequest;
import br.com.store.order.application.request.OrderRequest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Processor;
import org.springframework.cloud.stream.test.binder.MessageCollector;
import org.springframework.integration.annotation.Transformer;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ExampleTest {

    @Autowired
    private OrderInput processor;

    @Autowired
    private MessageCollector messageCollector;

    @Test
    @SuppressWarnings("unchecked")
    public void testWiring() {
        OrderRequest r = new OrderRequest();
        r.setIdClient("2121");
        r.setIdOrder(5);
        r.setOrderDate("2020-04-01");
        r.setTotalPrice(55.56);
        OrderItemRequest i = new OrderItemRequest();
        i.setPrice(4343.4);
        i.setProductName("PRODUTO");
        r.setItems(Arrays.asList(i));
        Message<OrderRequest> message = new GenericMessage<>(r);
        processor.receive().send(message);
        Message<OrderRequest> received = (Message<OrderRequest>) messageCollector.forChannel(processor.create()).poll();
        assertThat(received.getPayload(), equalTo("hello world"));
    }


//    @SpringBootApplication
//    @EnableBinding(OrderInput.class)
//    public static class MyProcessor {
//
//        @Autowired
//        private OrderInput channels;
//
//        @Transformer(inputChannel = OrderInput.RECEIVE, outputChannel = OrderInput.CREATE)
//        public Message<OrderRequest> transform(Message<OrderRequest> in) {
//            Message<OrderRequest> message = MessageBuilder.withPayload(in.getPayload())
//                    .build();
//            return message;
//        }
//    }
}