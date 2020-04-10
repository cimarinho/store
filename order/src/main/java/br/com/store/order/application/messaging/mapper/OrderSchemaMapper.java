package br.com.store.order.application.messaging.mapper;

import br.com.store.order.application.avro.OrderInputAvro;
import br.com.store.order.service.model.OrderModel;
import br.com.store.order.service.model.OrderModelResponse;
import org.mapstruct.Mapper;

@Mapper
public interface OrderSchemaMapper {

    OrderModel mapToRequest(OrderInputAvro orderSchema);

    OrderInputAvro mapToSchema(OrderModelResponse orderResponse);
}
