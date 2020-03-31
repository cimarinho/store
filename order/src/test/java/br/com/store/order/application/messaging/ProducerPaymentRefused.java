package br.com.store.order.application.messaging;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Properties;
import java.util.UUID;

public class ProducerPaymentRefused {

    public static void main(String[] args) {

        final Logger logger = LoggerFactory.getLogger(ProducerCreate.class);

        String bootstrapServers = "127.0.0.1:9092";

        // create Producer properties
        Properties properties = new Properties();
        properties.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        properties.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());

        // create the producer
        KafkaProducer<String, String> producer = new KafkaProducer<String, String>(properties);

        String jsonTwiter1 = "{\"idPayment\": 22, \"status\": \"REFUSED\", \"order\": {\"idOrder\" : 1,\t\"totalPrice\" :7676.7,\t\"orderDate\" : \"2016-02-20\",\t\"idClient\": \"2121\",\t\"items\" : [ { \"productName\": \"caneta\", \"price\": 121.5  }]}}";

        boolean t = true;

        // create a producer record
        ProducerRecord<String, String> record;
        final String mensageKey = UUID.randomUUID().toString();
        record = new ProducerRecord<String, String>("REFUSED_PAYMENT", mensageKey, jsonTwiter1);
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


        // flush data
        producer.flush();
        // flush and close producer
        producer.close();

    }

}
