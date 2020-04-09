package br.com.store.order.service.impl;

import br.com.store.order.application.request.OrderRequest;
import br.com.store.order.application.request.OrderResponse;
import br.com.store.order.domain.entity.LogDomain;
import br.com.store.order.domain.entity.OrderDomain;
import br.com.store.order.domain.repository.MongoLogDomainRepository;
import br.com.store.order.domain.repository.MongoOrderDomainRepository;
import br.com.store.order.service.OrderService;
import br.com.store.order.service.mapper.OrderMapper;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl  implements OrderService {

    @Autowired
    private MongoOrderDomainRepository orderRepository;

    @Autowired
    private MongoLogDomainRepository logRepository;

    private OrderMapper orderMapper = Mappers.getMapper(OrderMapper.class);

    public OrderResponse createOrder(OrderRequest request) {
        OrderDomain orderDomain = getOrderDomain(request).createOrder();
        this.orderRepository.save(orderDomain);
        return orderMapper.mapToResponse(orderDomain);
    }

    public OrderResponse completeOrder(OrderRequest request) {
        OrderDomain orderDomain = getOrderDomain(request).completeOrder();
        this.orderRepository.save(orderDomain);
        return orderMapper.mapToResponse(orderDomain);

    }

    public OrderResponse paymentRefused(OrderRequest request) {
        OrderDomain orderDomain = getOrderDomain(request).paymentRefused();
        LogDomain logDomain = LogDomain.builder().idOrder(orderDomain.getIdOrder()).error("paymentRefused").build();
        this.logRepository.save(logDomain);
        this.orderRepository.save(orderDomain);
        return orderMapper.mapToResponse(orderDomain);
    }

    OrderDomain getOrderDomain(OrderRequest request){
        return  orderMapper.mapToDomain(request);
    }

}
