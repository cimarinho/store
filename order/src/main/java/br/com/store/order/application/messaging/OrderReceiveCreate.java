package br.com.store.order.application.messaging;

import br.com.store.order.application.avro.OrderInputAvro;
import br.com.store.order.application.messaging.interfaces.OrderInput;
import br.com.store.order.application.messaging.mapper.OrderSchemaMapper;
import br.com.store.order.service.OrderService;
import br.com.store.order.service.model.OrderModel;
import br.com.store.order.service.model.OrderModelResponse;
import lombok.extern.slf4j.Slf4j;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.support.MessageBuilder;


@Slf4j
@EnableBinding({OrderInput.class})
public class OrderReceiveCreate {

    private OrderSchemaMapper mapper = Mappers.getMapper(OrderSchemaMapper.class);

    @Autowired
    private OrderService orderService;

    @StreamListener(OrderInput.RECEIVE)
    @SendTo(OrderInput.CREATE)
    public Message<OrderInputAvro> receive(final Message<OrderInputAvro> orderSchema, @Headers MessageHeaders headers) {
        log.info("receive {} correlation_id {}.", orderSchema, headers.get("correlation_id"));
        final OrderModel orderModel = this.mapper.mapToRequest(orderSchema.getPayload());
        final OrderModelResponse orderResponse = orderService.createOrder(orderModel);
        final OrderInputAvro orderSchema1 = this.mapper.mapToSchema(orderResponse);
        return MessageBuilder.withPayload(orderSchema1).setHeader("correlation_id", headers.get("correlation_id")).build();
    }
}