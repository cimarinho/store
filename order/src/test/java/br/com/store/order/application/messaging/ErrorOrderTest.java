package br.com.store.order.application.messaging;

import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.loader.FixtureFactoryLoader;
import br.com.store.order.application.avro.OrderInputAvro;
import br.com.store.order.application.messaging.interfaces.OrderInput;
import br.com.store.order.service.OrderService;
import br.com.store.order.template.messaging.avro.OrderInputAvroTemplate;
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

import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ErrorOrderTest {

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
    public void complete_payment() {
        OrderInputAvro orderInputAvro = Fixture.from(OrderInputAvro.class).gimme(OrderInputAvroTemplate.ORDER_AVRO_VALID);
        Message<OrderInputAvro> message = new GenericMessage<>(orderInputAvro);
        final boolean send = processor.errorPayment().send(message);
        assertTrue(send);
        verify(this.orderService, times(1)).errorOrder(any(), any());
    }

}
