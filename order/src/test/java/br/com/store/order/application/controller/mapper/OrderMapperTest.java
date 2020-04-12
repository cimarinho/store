package br.com.store.order.application.controller.mapper;

import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.loader.FixtureFactoryLoader;
import br.com.store.order.application.avro.OrderInputAvro;
import br.com.store.order.application.controller.request.OrderRequest;
import br.com.store.order.application.controller.response.OrderResponse;
import br.com.store.order.service.model.OrderModelResponse;
import br.com.store.order.template.controller.OrderRequestTemplate;
import br.com.store.order.template.service.OrderModelResponseTemplate;
import org.junit.Before;
import org.junit.Test;
import org.mapstruct.factory.Mappers;

import java.util.UUID;

import static org.junit.Assert.assertEquals;

public class OrderMapperTest {

    OrderMapper orderMapper = Mappers.getMapper(OrderMapper.class);

    @Before
    public void setup() {
        FixtureFactoryLoader.loadTemplates("br.com.store.order.template");
    }

    @Test
    public void mapToSchema() {
        String correlationId = UUID.randomUUID().toString();
        OrderRequest orderRequest = Fixture.from(OrderRequest.class).gimme(OrderRequestTemplate.ORDER_REQUEST_VALID);
        OrderInputAvro orderInputAvro = orderMapper.mapToSchema(orderRequest, correlationId);
        assertEquals(orderInputAvro.getId(), correlationId);
        assertEquals(orderInputAvro.getIdOrder(), Integer.valueOf(orderRequest.getIdOrder().intValue()));
        assertEquals(orderInputAvro.getOrderDate(), orderRequest.getOrderDate().toString());
        assertEquals(orderInputAvro.getIdClient(), orderRequest.getIdClient());
        assertEquals(orderInputAvro.getTotalPrice(), orderRequest.getTotalPrice());
        assertEquals(orderInputAvro.getItems().get(0).getPrice(), orderRequest.getItems().get(0).getPrice());
        assertEquals(orderInputAvro.getItems().get(0).getProductName(), orderRequest.getItems().get(0).getProductName());
    }

    @Test
   public void mapToResponse() {
        OrderModelResponse orderModelResponse = Fixture.from(OrderModelResponse.class).gimme(OrderModelResponseTemplate.ORDER_MODEL_RESPONSE_VALID);
        OrderResponse orderResponse = orderMapper.mapToResponse(orderModelResponse);
        assertEquals(orderResponse.getId(), orderModelResponse.getId());
        assertEquals(orderResponse.getStatus(), orderModelResponse.getStatus());
        assertEquals(orderResponse.getIdOrder(), orderModelResponse.getIdOrder());
        assertEquals(orderResponse.getOrderDate(), orderModelResponse.getOrderDate());
        assertEquals(orderResponse.getIdClient(), orderModelResponse.getIdClient());
        assertEquals(orderResponse.getTotalPrice(), orderModelResponse.getTotalPrice());
        assertEquals(orderResponse.getItems().get(0).getPrice(), orderModelResponse.getItems().get(0).getPrice());
        assertEquals(orderResponse.getItems().get(0).getProductName(), orderModelResponse.getItems().get(0).getProductName());
    }


}
