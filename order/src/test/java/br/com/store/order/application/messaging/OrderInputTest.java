package br.com.store.order.application.messaging;

import br.com.store.order.OrderApplication;
import br.com.store.order.application.messaging.interfaces.OrderInput;
import br.com.store.order.application.messaging.mock.CustomKafkaAvroDeserializer;
import br.com.store.order.application.messaging.type.IntegrationTest;
import br.com.store.order.application.request.OrderRequest;
import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.assertj.core.util.Lists;
import org.junit.After;
import org.junit.Before;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.test.EmbeddedKafkaBroker;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.kafka.test.utils.KafkaTestUtils;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;

//@SpringBootTest(classes = {OrderApplication.class}, webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
//@RunWith(SpringRunner.class)
//@Category(IntegrationTest.class)
//@EmbeddedKafka()
public class OrderInputTest {

//    @Autowired
//    private KafkaProperties kafkaProperties;
//    @Autowired
//    private EmbeddedKafkaBroker kafkaEmbedded;
//
//    protected Producer<String, OrderRequest> orderProducer;
//    protected Consumer<String, OrderRequest> orderConsumer;
//
//    @Before
//    public void setUp() {
//        Map<String, Object> senderProps = kafkaProperties.buildProducerProperties();
//
//        orderProducer = new KafkaProducer<>(senderProps);
//
//        Map<String, Object> configs = new HashMap<>(KafkaTestUtils.consumerProps("in-test-consumer", "false", kafkaEmbedded));
//        configs.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, org.apache.kafka.common.serialization.StringDeserializer.class);
//        configs.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, CustomKafkaAvroDeserializer.class);
//        configs.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
//        configs.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, true);
//        configs.put("schema.registry.url", "not-used");
//
//        orderConsumer = new DefaultKafkaConsumerFactory<String, OrderRequest>(configs).createConsumer("in-test-consumer", "10");
//
//        kafkaProperties.buildConsumerProperties();
//        orderConsumer.subscribe(Lists.newArrayList(OrderInput.CREATE));
//    }
//
//    @After
//    public void reset() {
//        orderConsumer.close();
//    }

}
