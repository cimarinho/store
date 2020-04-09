package client;


import br.com.store.order.application.request.OrderItemRequest;
import br.com.store.order.application.request.OrderRequest;
import io.confluent.kafka.serializers.AbstractKafkaAvroSerDeConfig;
import io.confluent.kafka.streams.serdes.avro.SpecificAvroSerde;
import org.apache.kafka.clients.producer.*;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.StreamsConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;

import java.util.Arrays;
import java.util.Properties;

public class ProducerCreate {

    public static void main(String[] args) {

        final Logger logger = LoggerFactory.getLogger(ProducerCreate.class);

        String bootstrapServers = "127.0.0.1:9092";
        // schema registry url.
        String registry = "http://127.0.0.1:8081";

        // create Producer properties
        Properties properties = new Properties();
        properties.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        properties.put(AbstractKafkaAvroSerDeConfig.SCHEMA_REGISTRY_URL_CONFIG, registry);
        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringSerializer");
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, "io.confluent.kafka.serializers.KafkaAvroSerializer");
        properties.put(ProducerConfig.ACKS_CONFIG, "0");
        properties.put(ProducerConfig.RETRIES_CONFIG, "0");
        properties.put(ProducerConfig.BATCH_SIZE_CONFIG, "16384");
        properties.put(ProducerConfig.LINGER_MS_CONFIG, "1");
        properties.put(ProducerConfig.BUFFER_MEMORY_CONFIG, "33554432");
        properties.put(JsonSerializer.ADD_TYPE_INFO_HEADERS, false);
        properties.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.StringSerde.class);
        properties.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, SpecificAvroSerde.class);

        // create the producer
        KafkaProducer<String, OrderRequest> producer = new KafkaProducer<>(properties);

        OrderRequest r = new OrderRequest();
        r.setIdClient("2121");
        r.setIdOrder(5);
        r.setOrderDate("2020-04-01");
        r.setTotalPrice(55.56);
        OrderItemRequest i = new OrderItemRequest();
        i.setPrice(4343.4);
        i.setProductName("PRODUTO");
        r.setItems(Arrays.asList(i));
        // create a producer record
        ProducerRecord<String, OrderRequest> record;
        record = new ProducerRecord<>("receive_order",  r);
//        record = new ProducerRecord<>("receive_order",  r);

        // send data - asynchronous
        producer.send(record, (recordMetadata, e) -> {
            // executes every time a record is successfully sent or an exception is thrown
            if (e == null) {
                // the record was successfully sent
                logger.info("Received new metadata. \n" +
                        "Topic:" + recordMetadata.topic() + "\n" +
                        "Partition: " + recordMetadata.partition() + "\n" +
                        "Offset: " + recordMetadata.offset() + "\n" +
                        "Timestamp: " + recordMetadata.timestamp());
            } else {
                logger.error("Error while producing", e);
            }
        });

        org.apache.kafka.common.serialization.StringDeserializer a;


        // flush data
        producer.flush();
        // flush and close producer
        producer.close();

    }

}
