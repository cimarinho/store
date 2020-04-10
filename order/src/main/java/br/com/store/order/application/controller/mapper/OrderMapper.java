package br.com.store.order.application.controller.mapper;

import br.com.store.order.application.avro.OrderInputAvro;
import br.com.store.order.application.controller.request.OrderRequest;
import br.com.store.order.application.controller.response.OrderResponse;
import br.com.store.order.service.model.OrderModelResponse;
import org.mapstruct.Mapper;

@Mapper
public interface OrderMapper {

    OrderInputAvro mapToSchema(OrderRequest request, String id);

    OrderResponse mapToResponse(OrderModelResponse orderModelResponse);
}
