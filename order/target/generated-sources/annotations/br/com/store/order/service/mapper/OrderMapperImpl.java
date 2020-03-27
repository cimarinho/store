package br.com.store.order.service.mapper;

import br.com.store.order.adapter.Repository;
import br.com.store.order.domain.OrderDomain;
import br.com.store.order.domain.OrderDomain.OrderDomainBuilder;
import br.com.store.order.port.request.OrderRequest;
import br.com.store.order.port.request.OrderRequest.OrderItemRequest;
import br.com.store.order.port.response.OrderResponse;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-03-26T21:13:22-0300",
    comments = "version: 1.3.1.Final, compiler: javac, environment: Java 1.8.0_201 (Oracle Corporation)"
)
public class OrderMapperImpl implements OrderMapper {

    @Override
    public OrderDomain mapToDomain(OrderRequest orderRequest, Repository<OrderDomain, Long> repository) {
        if ( orderRequest == null && repository == null ) {
            return null;
        }

        OrderDomainBuilder orderDomain = OrderDomain.builder();

        if ( orderRequest != null ) {
            orderDomain.idOrder( orderRequest.getIdOrder() );
            orderDomain.totalPrice( orderRequest.getTotalPrice() );
            orderDomain.orderDate( orderRequest.getOrderDate() );
            orderDomain.idClient( orderRequest.getIdClient() );
            List<OrderItemRequest> list = orderRequest.getItems();
            if ( list != null ) {
                orderDomain.items( new ArrayList<OrderItemRequest>( list ) );
            }
        }
        if ( repository != null ) {
            orderDomain.repository( repository );
        }

        return orderDomain.build();
    }

    @Override
    public OrderResponse mapToResponse(OrderDomain orderDomain) {
        if ( orderDomain == null ) {
            return null;
        }

        OrderResponse orderResponse = new OrderResponse();

        return orderResponse;
    }
}
