package br.com.store.order.domain.mapper;

import br.com.store.order.domain.OrderDomain;
import br.com.store.order.application.request.OrderRequest;
import br.com.store.order.application.response.OrderResponse;
import br.com.store.order.domain.repository.Repository;
import org.mapstruct.Mapper;

@Mapper
public interface OrderMapper {

    OrderDomain mapToDomain(OrderRequest orderRequest, Repository<OrderDomain, Long> repository);

    OrderResponse mapToResponse (OrderDomain orderDomain);
}
