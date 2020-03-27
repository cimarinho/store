package br.com.store.order.config;

import org.springframework.context.annotation.Bean;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.messaging.MessageChannel;

public class MessageChannelConfig {

    @Bean
    public MessageChannel create() {
        return new DirectChannel();
    }

}
