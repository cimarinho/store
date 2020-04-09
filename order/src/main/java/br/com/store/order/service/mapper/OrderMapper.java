package br.com.store.order.service.mapper;

import br.com.store.order.application.request.OrderRequest;
import br.com.store.order.application.request.OrderResponse;
import br.com.store.order.domain.entity.OrderDomain;
import org.mapstruct.Mapper;

@Mapper
public interface OrderMapper {

    OrderDomain mapToDomain(OrderRequest orderRequest);

    OrderResponse mapToResponse (OrderDomain orderDomain);
}
