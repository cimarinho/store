package br.com.store.order.service.mapper;

import br.com.store.order.domain.entity.OrderDomain;
import br.com.store.order.domain.entity.OrderDomain.OrderDomainBuilder;
import br.com.store.order.domain.entity.OrderItemDomain;
import br.com.store.order.domain.entity.OrderItemDomain.OrderItemDomainBuilder;
import br.com.store.order.service.model.OrderModel;
import br.com.store.order.service.model.OrderModel.OrderModelItem;
import br.com.store.order.service.model.OrderModelResponse;
import br.com.store.order.service.model.OrderModelResponse.OrderModelItemResponse;
import br.com.store.order.service.model.OrderModelResponse.OrderModelItemResponse.OrderModelItemResponseBuilder;
import br.com.store.order.service.model.OrderModelResponse.OrderModelResponseBuilder;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-04-10T12:54:27-0300",
    comments = "version: 1.3.1.Final, compiler: javac, environment: Java 9.0.4 (Oracle Corporation)"
)
public class OrderMapperImpl implements OrderMapper {

    @Override
    public OrderDomain mapToDomain(OrderModel orderRequest) {
        if ( orderRequest == null ) {
            return null;
        }

        OrderDomainBuilder orderDomain = OrderDomain.builder();

        orderDomain.id( orderRequest.getId() );
        orderDomain.idOrder( orderRequest.getIdOrder() );
        orderDomain.totalPrice( orderRequest.getTotalPrice() );
        if ( orderRequest.getOrderDate() != null ) {
            orderDomain.orderDate( DateTimeFormatter.ISO_LOCAL_DATE.format( orderRequest.getOrderDate() ) );
        }
        orderDomain.idClient( orderRequest.getIdClient() );
        orderDomain.items( orderModelItemListToOrderItemDomainList( orderRequest.getItems() ) );

        return orderDomain.build();
    }

    @Override
    public OrderModelResponse mapToResponse(OrderDomain orderDomain) {
        if ( orderDomain == null ) {
            return null;
        }

        OrderModelResponseBuilder orderModelResponse = OrderModelResponse.builder();

        orderModelResponse.id( orderDomain.getId() );
        orderModelResponse.idClient( orderDomain.getIdClient() );
        orderModelResponse.idOrder( orderDomain.getIdOrder() );
        if ( orderDomain.getOrderDate() != null ) {
            orderModelResponse.orderDate( LocalDate.parse( orderDomain.getOrderDate() ) );
        }
        orderModelResponse.totalPrice( orderDomain.getTotalPrice() );
        orderModelResponse.status( orderDomain.getStatus() );
        orderModelResponse.items( orderItemDomainListToOrderModelItemResponseList( orderDomain.getItems() ) );

        return orderModelResponse.build();
    }

    protected OrderItemDomain orderModelItemToOrderItemDomain(OrderModelItem orderModelItem) {
        if ( orderModelItem == null ) {
            return null;
        }

        OrderItemDomainBuilder orderItemDomain = OrderItemDomain.builder();

        orderItemDomain.productName( orderModelItem.getProductName() );
        if ( orderModelItem.getPrice() != null ) {
            orderItemDomain.price( BigDecimal.valueOf( orderModelItem.getPrice() ) );
        }

        return orderItemDomain.build();
    }

    protected List<OrderItemDomain> orderModelItemListToOrderItemDomainList(List<OrderModelItem> list) {
        if ( list == null ) {
            return null;
        }

        List<OrderItemDomain> list1 = new ArrayList<OrderItemDomain>( list.size() );
        for ( OrderModelItem orderModelItem : list ) {
            list1.add( orderModelItemToOrderItemDomain( orderModelItem ) );
        }

        return list1;
    }

    protected OrderModelItemResponse orderItemDomainToOrderModelItemResponse(OrderItemDomain orderItemDomain) {
        if ( orderItemDomain == null ) {
            return null;
        }

        OrderModelItemResponseBuilder orderModelItemResponse = OrderModelItemResponse.builder();

        orderModelItemResponse.productName( orderItemDomain.getProductName() );
        if ( orderItemDomain.getPrice() != null ) {
            orderModelItemResponse.price( orderItemDomain.getPrice().doubleValue() );
        }

        return orderModelItemResponse.build();
    }

    protected List<OrderModelItemResponse> orderItemDomainListToOrderModelItemResponseList(List<OrderItemDomain> list) {
        if ( list == null ) {
            return null;
        }

        List<OrderModelItemResponse> list1 = new ArrayList<OrderModelItemResponse>( list.size() );
        for ( OrderItemDomain orderItemDomain : list ) {
            list1.add( orderItemDomainToOrderModelItemResponse( orderItemDomain ) );
        }

        return list1;
    }
}
