package br.com.store.order.application.messaging;

import br.com.store.order.application.avro.OrderInputAvro;
import br.com.store.order.application.avro.OrderInputItemAvro;
import br.com.store.order.application.messaging.interfaces.OrderInput;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.stream.test.binder.MessageCollector;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;

import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TestDoc  {

    @Autowired
    private OrderInput processor;

    @Autowired
    private MessageCollector messageCollector;

    @Test
    public void testWiring() {
        OrderInputAvro r = new OrderInputAvro();
        r.setIdClient("2121");
        r.setIdOrder(5);
        r.setOrderDate("2020-04-01");
        r.setTotalPrice(55.56);
        OrderInputItemAvro i = new OrderInputItemAvro();
        i.setPrice(4343.4);
        i.setProductName("PRODUTO");
        r.setItems(Arrays.asList(i));
        Message<OrderInputAvro> message = new GenericMessage<OrderInputAvro>(r);
        processor.receive().send(message);
        Message<OrderInputAvro> received = (Message<OrderInputAvro>) messageCollector.forChannel(processor.create()).poll();

        assertNotNull(received.getPayload());
    }

}
