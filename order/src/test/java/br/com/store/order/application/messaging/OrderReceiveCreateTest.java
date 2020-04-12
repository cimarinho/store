package br.com.store.order.application.messaging;

import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.loader.FixtureFactoryLoader;
import br.com.store.order.application.avro.OrderInputAvro;
import br.com.store.order.application.messaging.interfaces.OrderInput;
import br.com.store.order.service.OrderService;
import br.com.store.order.service.model.OrderModelResponse;
import br.com.store.order.template.messaging.avro.OrderInputAvroTemplate;
import br.com.store.order.template.service.OrderModelResponseTemplate;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.cloud.stream.test.binder.MessageCollector;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class OrderReceiveCreateTest {

    @Autowired
    private OrderInput processor;

    @Autowired
    private MessageCollector messageCollector;

    @MockBean
    private OrderService orderService;

    @Before
    public void setup() {
        FixtureFactoryLoader.loadTemplates("br.com.store.order.template");
    }


    @Test
    public void receive() {
        OrderInputAvro orderInputAvro = Fixture.from(OrderInputAvro.class).gimme(OrderInputAvroTemplate.ORDER_AVRO_VALID);
        OrderModelResponse orderModelResponse = Fixture.from(OrderModelResponse.class).gimme(OrderModelResponseTemplate.ORDER_MODEL_RESPONSE_VALID);
        Message<OrderInputAvro> message = new GenericMessage<>(orderInputAvro);
        when(orderService.createOrder(any())).thenReturn(orderModelResponse);
        processor.receive().send(message);
        Message<OrderInputAvro> received = (Message<OrderInputAvro>) messageCollector.forChannel(processor.create()).poll();
        assertNotNull(received.getPayload());
        assertEquals(received.getPayload().getId(), orderInputAvro.getId());
    }
}