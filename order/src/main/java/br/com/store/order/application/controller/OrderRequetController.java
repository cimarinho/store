package br.com.store.order.application.controller;

import br.com.store.order.application.avro.OrderInputAvro;
import br.com.store.order.application.controller.interfaces.AbstractController;
import br.com.store.order.application.controller.mapper.OrderMapper;
import br.com.store.order.application.controller.request.OrderRequest;
import br.com.store.order.application.controller.response.OrderCreateResponse;
import br.com.store.order.application.controller.response.OrderResponse;
import br.com.store.order.application.messaging.interfaces.OrderInput;
import br.com.store.order.service.OrderService;
import br.com.store.order.service.model.OrderModelResponse;
import lombok.extern.slf4j.Slf4j;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Slf4j
@EnableBinding({OrderInput.class})
@RestController
public class OrderRequetController implements AbstractController {

    private OrderMapper mapper = Mappers.getMapper(OrderMapper.class);

    @Autowired
    @Qualifier(value = OrderInput.RECEIVE)
    MessageChannel messageChannel;

    @Autowired
    private OrderService orderService;

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public HttpEntity<OrderCreateResponse> create(@RequestBody OrderRequest request) {
        String correlationId = UUID.randomUUID().toString();
        final OrderInputAvro orderSchema = this.mapper.mapToSchema(request, correlationId);
        messageChannel.send(MessageBuilder.withPayload(orderSchema).setHeader("correlation_id", correlationId).build());
        return getOk(new OrderCreateResponse(correlationId));
    }

    @GetMapping("/{id}")
    public HttpEntity<OrderResponse> order(@PathVariable String id) {
        OrderModelResponse order = orderService.getOrder(id);
        OrderResponse orderResponse = this.mapper.mapToResponse(order);
        return getOk(orderResponse);
    }
}