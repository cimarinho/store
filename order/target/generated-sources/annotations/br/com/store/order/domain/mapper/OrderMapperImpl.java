package br.com.store.order.domain.mapper;

import br.com.store.order.application.request.OrderRequest;
import br.com.store.order.application.request.OrderRequest.OrderItemRequest;
import br.com.store.order.application.response.OrderResponse;
import br.com.store.order.application.response.OrderResponse.OrderItemRequest.OrderItemRequestBuilder;
import br.com.store.order.application.response.OrderResponse.OrderResponseBuilder;
import br.com.store.order.domain.OrderDomain;
import br.com.store.order.domain.OrderDomain.OrderDomainBuilder;
import br.com.store.order.domain.repository.Repository;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-03-30T20:28:45-0300",
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

        OrderResponseBuilder orderResponse = OrderResponse.builder();

        orderResponse.idOrder( orderDomain.getIdOrder() );
        if ( orderDomain.getTotalPrice() != null ) {
            orderResponse.totalPrice( BigDecimal.valueOf( orderDomain.getTotalPrice() ) );
        }
        orderResponse.orderDate( orderDomain.getOrderDate() );
        orderResponse.idClient( orderDomain.getIdClient() );
        if ( orderDomain.getStatus() != null ) {
            orderResponse.status( orderDomain.getStatus().name() );
        }
        orderResponse.items( orderItemRequestListToOrderItemRequestList( orderDomain.getItems() ) );

        return orderResponse.build();
    }

    protected br.com.store.order.application.response.OrderResponse.OrderItemRequest orderItemRequestToOrderItemRequest(OrderItemRequest orderItemRequest) {
        if ( orderItemRequest == null ) {
            return null;
        }

        OrderItemRequestBuilder orderItemRequest1 = br.com.store.order.application.response.OrderResponse.OrderItemRequest.builder();

        orderItemRequest1.productName( orderItemRequest.getProductName() );
        if ( orderItemRequest.getPrice() != null ) {
            orderItemRequest1.price( BigDecimal.valueOf( orderItemRequest.getPrice() ) );
        }

        return orderItemRequest1.build();
    }

    protected List<br.com.store.order.application.response.OrderResponse.OrderItemRequest> orderItemRequestListToOrderItemRequestList(List<OrderItemRequest> list) {
        if ( list == null ) {
            return null;
        }

        List<br.com.store.order.application.response.OrderResponse.OrderItemRequest> list1 = new ArrayList<br.com.store.order.application.response.OrderResponse.OrderItemRequest>( list.size() );
        for ( OrderItemRequest orderItemRequest : list ) {
            list1.add( orderItemRequestToOrderItemRequest( orderItemRequest ) );
        }

        return list1;
    }
}
