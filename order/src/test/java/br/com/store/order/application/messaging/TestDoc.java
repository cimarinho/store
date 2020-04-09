package br.com.store.order.application.messaging;

import br.com.store.order.application.Teste;
import br.com.store.order.application.messaging.interfaces.OrderInput;
import br.com.store.order.application.request.OrderItemRequest;
import br.com.store.order.application.request.OrderRequest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.test.binder.MessageCollector;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;

@RunWith(SpringRunner.class)
//@EmbeddedKafka(brokerProperties = {
//        "log.dir=/tmp/kafka-data"
//})
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TestDoc  {

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
        Message<OrderRequest> message = new GenericMessage<OrderRequest>(r);
        processor.receive().send(message);
 //       Message<OrderRequest> received = (Message<OrderRequest>) messageCollector.forChannel(processor.create()).poll();

        System.out.println();
    }

    @Test
    @SuppressWarnings("unchecked")
    public void teste() {
        Teste t= new Teste();t.setNome("FFFF");
        Message<Teste> message = new GenericMessage<Teste>(t);
        processor.receive().send(message);

        Message<Teste> received = (Message<Teste>) messageCollector.forChannel(processor.create()).poll();

        System.out.println();
    }


}
