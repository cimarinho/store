package br.com.store.order.service.mapper;

import br.com.store.order.adapter.Repository;
import br.com.store.order.adapter.mongo.MongoRepository;
import br.com.store.order.domain.OrderDomain;
import br.com.store.order.port.request.OrderRequest;
import br.com.store.order.port.response.OrderResponse;
import org.mapstruct.Mapper;

@Mapper
public interface OrderMapper {

    OrderDomain mapToDomain(OrderRequest orderRequest, Repository<OrderDomain, Long> repository);

    OrderResponse mapToResponse (OrderDomain orderDomain);
}
