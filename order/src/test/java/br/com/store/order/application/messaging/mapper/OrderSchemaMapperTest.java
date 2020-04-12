package br.com.store.order.application.messaging.mapper;

import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.loader.FixtureFactoryLoader;
import br.com.store.order.application.avro.OrderInputAvro;
import br.com.store.order.service.model.OrderModel;
import br.com.store.order.service.model.OrderModelResponse;
import br.com.store.order.template.messaging.avro.OrderInputAvroTemplate;
import br.com.store.order.template.service.OrderModelResponseTemplate;
import br.com.store.order.template.service.OrderModelTemplate;
import org.junit.Before;
import org.junit.Test;
import org.mapstruct.factory.Mappers;

import static org.junit.Assert.assertEquals;

public class OrderSchemaMapperTest {

    private OrderSchemaMapper mapper = Mappers.getMapper(OrderSchemaMapper.class);

    @Before
    public void setup() {
        FixtureFactoryLoader.loadTemplates("br.com.store.order.template");
    }

    @Test
    public void mapToRequest() {
        OrderInputAvro orderInputAvro = Fixture.from(OrderInputAvro.class).gimme(OrderInputAvroTemplate.ORDER_AVRO_VALID);
        final OrderModel orderModel = mapper.mapToRequest(orderInputAvro);
        assertEquals(orderModel.getId(), orderInputAvro.getId());
        assertEquals(orderModel.getOrderDate().toString(), orderInputAvro.getOrderDate());
        assertEquals(orderModel.getIdClient(), orderInputAvro.getIdClient());
        assertEquals(orderModel.getIdOrder(), Long.valueOf(orderInputAvro.getIdOrder()));
        assertEquals(orderModel.getTotalPrice(), orderInputAvro.getTotalPrice());
        assertEquals(orderModel.getItems().get(0).getPrice(), orderInputAvro.getItems().get(0).getPrice());
        assertEquals(orderModel.getItems().get(0).getProductName(), orderInputAvro.getItems().get(0).getProductName());
    }

    @Test
    public void mapToSchema() {
        OrderModelResponse orderModel = Fixture.from(OrderModelResponse.class).gimme(OrderModelResponseTemplate.ORDER_MODEL_RESPONSE_VALID);
        final OrderInputAvro orderInputAvro = mapper.mapToSchema(orderModel);
        assertEquals(orderModel.getId(), orderInputAvro.getId());
        assertEquals(orderModel.getOrderDate().toString(), orderInputAvro.getOrderDate());
        assertEquals(orderModel.getIdClient(), orderInputAvro.getIdClient());
        assertEquals(orderModel.getIdOrder(), Long.valueOf(orderInputAvro.getIdOrder()));
        assertEquals(orderModel.getTotalPrice(), orderInputAvro.getTotalPrice());
        assertEquals(orderModel.getItems().get(0).getPrice(), orderInputAvro.getItems().get(0).getPrice());
        assertEquals(orderModel.getItems().get(0).getProductName(), orderInputAvro.getItems().get(0).getProductName());
    }

}
