package br.com.store.order.service.mapper;

import br.com.store.order.domain.entity.OrderDomain;
import br.com.store.order.service.model.OrderModel;
import br.com.store.order.service.model.OrderModelResponse;
import org.mapstruct.Mapper;

@Mapper
public interface OrderMapper {

    OrderDomain mapToDomain(OrderModel orderRequest);

    OrderModelResponse mapToResponse (OrderDomain orderDomain);

}
