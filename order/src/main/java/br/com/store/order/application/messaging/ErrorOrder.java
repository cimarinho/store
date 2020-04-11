package br.com.store.order.application.messaging;

import br.com.store.order.application.avro.OrderInputAvro;
import br.com.store.order.application.messaging.interfaces.OrderInput;
import br.com.store.order.application.messaging.mapper.OrderSchemaMapper;
import br.com.store.order.domain.entity.type.OrderErrorStatus;
import br.com.store.order.service.OrderService;
import br.com.store.order.service.model.OrderModel;
import lombok.extern.slf4j.Slf4j;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.handler.annotation.Headers;

@Slf4j
@EnableBinding({OrderInput.class})
public class ErrorOrder {

    private OrderSchemaMapper mapper = Mappers.getMapper(OrderSchemaMapper.class);

    @Autowired
    private OrderService orderService;

    @StreamListener(OrderInput.ERROR_PAYMENT)
    public void completePayment(final Message<OrderInputAvro> orderSchema, @Headers MessageHeaders headers) {
        log.info("receive {} correlation_id {}.", orderSchema, headers.get("correlation_id"));
        final OrderModel orderModel = this.mapper.mapToRequest(orderSchema.getPayload());
        orderService.errorOrder(orderModel, OrderErrorStatus.PAYMENT);
    }
}
