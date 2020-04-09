package br.com.store.order.service.mapper;

import br.com.store.order.application.request.OrderItemRequest;
import br.com.store.order.application.request.OrderItemResponse;
import br.com.store.order.application.request.OrderRequest;
import br.com.store.order.application.request.OrderResponse;
import br.com.store.order.application.request.OrderResponse.Builder;
import br.com.store.order.domain.entity.OrderDomain;
import br.com.store.order.domain.entity.OrderDomain.OrderDomainBuilder;
import br.com.store.order.domain.entity.OrderItemDomain;
import br.com.store.order.domain.entity.OrderItemDomain.OrderItemDomainBuilder;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-04-09T06:52:14-0300",
    comments = "version: 1.3.1.Final, compiler: javac, environment: Java 1.8.0_201 (Oracle Corporation)"
)
public class OrderMapperImpl implements OrderMapper {

    @Override
    public OrderDomain mapToDomain(OrderRequest orderRequest) {
        if ( orderRequest == null ) {
            return null;
        }

        OrderDomainBuilder orderDomain = OrderDomain.builder();

        if ( orderRequest.getIdOrder() != null ) {
            orderDomain.idOrder( orderRequest.getIdOrder().longValue() );
        }
        orderDomain.totalPrice( orderRequest.getTotalPrice() );
        orderDomain.orderDate( orderRequest.getOrderDate() );
        orderDomain.idClient( orderRequest.getIdClient() );
        orderDomain.items( orderItemRequestListToOrderItemDomainList( orderRequest.getItems() ) );

        return orderDomain.build();
    }

    @Override
    public OrderResponse mapToResponse(OrderDomain orderDomain) {
        if ( orderDomain == null ) {
            return null;
        }

        Builder orderResponse = OrderResponse.newBuilder();

        if ( orderDomain.getIdOrder() != null ) {
            orderResponse.setIdOrder( orderDomain.getIdOrder().intValue() );
        }
        if ( orderDomain.getTotalPrice() != null ) {
            orderResponse.setTotalPrice( orderDomain.getTotalPrice() );
        }
        orderResponse.setOrderDate( orderDomain.getOrderDate() );
        orderResponse.setIdClient( orderDomain.getIdClient() );
        if ( orderDomain.getStatus() != null ) {
            orderResponse.setStatus( orderDomain.getStatus().name() );
        }
        orderResponse.setItems( orderItemDomainListToOrderItemResponseList( orderDomain.getItems() ) );

        return orderResponse.build();
    }

    protected OrderItemDomain orderItemRequestToOrderItemDomain(OrderItemRequest orderItemRequest) {
        if ( orderItemRequest == null ) {
            return null;
        }

        OrderItemDomainBuilder orderItemDomain = OrderItemDomain.builder();

        orderItemDomain.productName( orderItemRequest.getProductName() );
        if ( orderItemRequest.getPrice() != null ) {
            orderItemDomain.price( BigDecimal.valueOf( orderItemRequest.getPrice() ) );
        }

        return orderItemDomain.build();
    }

    protected List<OrderItemDomain> orderItemRequestListToOrderItemDomainList(List<OrderItemRequest> list) {
        if ( list == null ) {
            return null;
        }

        List<OrderItemDomain> list1 = new ArrayList<OrderItemDomain>( list.size() );
        for ( OrderItemRequest orderItemRequest : list ) {
            list1.add( orderItemRequestToOrderItemDomain( orderItemRequest ) );
        }

        return list1;
    }

    protected OrderItemResponse orderItemDomainToOrderItemResponse(OrderItemDomain orderItemDomain) {
        if ( orderItemDomain == null ) {
            return null;
        }

        br.com.store.order.application.request.OrderItemResponse.Builder orderItemResponse = OrderItemResponse.newBuilder();

        orderItemResponse.setProductName( orderItemDomain.getProductName() );
        if ( orderItemDomain.getPrice() != null ) {
            orderItemResponse.setPrice( orderItemDomain.getPrice().doubleValue() );
        }

        return orderItemResponse.build();
    }

    protected List<OrderItemResponse> orderItemDomainListToOrderItemResponseList(List<OrderItemDomain> list) {
        if ( list == null ) {
            return null;
        }

        List<OrderItemResponse> list1 = new ArrayList<OrderItemResponse>( list.size() );
        for ( OrderItemDomain orderItemDomain : list ) {
            list1.add( orderItemDomainToOrderItemResponse( orderItemDomain ) );
        }

        return list1;
    }
}
