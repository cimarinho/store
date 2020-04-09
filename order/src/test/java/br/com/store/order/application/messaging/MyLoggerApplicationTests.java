package br.com.store.order.application.messaging;

import br.com.store.order.OrderApplication;
import br.com.store.order.application.messaging.interfaces.OrderInput;
import br.com.store.order.application.request.OrderItemRequest;
import br.com.store.order.application.request.OrderRequest;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.stream.test.binder.MessageCollector;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.concurrent.BlockingQueue;

import static java.lang.Thread.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {OrderApplication.class}, webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
//@DirtiesContext
public class MyLoggerApplicationTests {

    @Autowired
    private OrderInput pipe;

    @Autowired
    private MessageCollector messageCollector;

    @Before
    public void setup(){

    }

    @Test
    public void whenSendMessage_thenResponseShouldUpdateText() {
        OrderRequest r = new OrderRequest();
        r.setIdClient("2121");
        r.setIdOrder(5);
        r.setOrderDate("2020-04-01");
        r.setTotalPrice(55.56);
        OrderItemRequest i = new OrderItemRequest();
        i.setPrice(4343.4);
        i.setProductName("PRODUTO");
        r.setItems(Arrays.asList(i));

        pipe.receive()
                .send(MessageBuilder.withPayload(r)
                        .build());

       // BlockingQueue<Message<?>> messages = messageCollector.forChannel(pipe.orderLog());



//        Object payload = messageCollector.forChannel(pipe.completeShipping())
//                .poll()
//                .getPayload();

        System.out.println();
//        assertEquals("[1]: This is my message", payload.toString());
    }
}
