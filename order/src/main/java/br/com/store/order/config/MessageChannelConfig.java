package br.com.store.order.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.kafka.support.converter.MessageConverter;
import org.springframework.messaging.MessageChannel;
import org.springframework.util.MimeType;

@Configuration
public class MessageChannelConfig {

//    @Bean
//    public MessageConverter userMessageConverter() {
//        AvroSchemaMessageConverter converter = new AvroSchemaMessageConverter(MimeType.valueOf("avro/bytes"));
//        converter.setSchemaLocation(new ClassPathResource("schemas/User.avro"));
//        return converter;
//    }
//
//    @Bean
//    public MessageConverter userMessageConverter() {
//        AvroSchemaMessageConverter converter = new AvroSchemaMessageConverter(MimeType.valueOf("avro/bytes"));
//        converter.setSchemaLocation(new ClassPathResource("schemas/User.avro"));
//        return converter;
//    }

}