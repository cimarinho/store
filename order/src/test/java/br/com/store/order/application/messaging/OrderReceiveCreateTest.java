package br.com.store.order.application.messaging;

import br.com.store.order.application.messaging.interfaces.OrderInput;
import br.com.store.order.application.request.OrderItemRequest;
import br.com.store.order.application.request.OrderRequest;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.test.utils.KafkaTestUtils;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

public class OrderReceiveCreateTest extends OrderInputTest {

//    @Autowired
//    private KafkaTemplate<String, OrderRequest> kafkaTemplateEvent1;

   // @Test
//    public void should_send_event1() {
//        OrderRequest r = new OrderRequest();
//        r.setIdClient("2121");
//        r.setIdOrder(5);
//        r.setOrderDate("2020-04-01");
//        r.setTotalPrice(55.56);
//        OrderItemRequest i = new OrderItemRequest();
//        i.setPrice(4343.4);
//        i.setProductName("PRODUTO");
//        r.setItems(Arrays.asList(i));
//        kafkaTemplateEvent1.send(OrderInput.RECEIVE, r);
//
//        ConsumerRecord<String, OrderRequest> singleRecord = KafkaTestUtils.getSingleRecord(orderConsumer, OrderInput.CREATE);
//        assertThat(singleRecord).isNotNull();
//    }

//    @Test
//    public void should_send_event2() {
//        messageProducer.sendEvent2();
//
//        ConsumerRecord<String, Event2> singleRecord = KafkaTestUtils.getSingleRecord(event2Consumer, Constants.EVENT_2_TOPIC);
//        assertThat(singleRecord).isNotNull();
//    }
}