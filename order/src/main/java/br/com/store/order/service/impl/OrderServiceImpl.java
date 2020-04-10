package br.com.store.order.service.impl;


import br.com.store.order.config.exception.DomainException;
import br.com.store.order.domain.entity.OrderDomain;
import br.com.store.order.domain.entity.type.OrderErrorStatus;
import br.com.store.order.domain.repository.MongoOrderDomainRepository;
import br.com.store.order.service.OrderService;
import br.com.store.order.service.mapper.OrderMapper;
import br.com.store.order.service.model.OrderModel;
import br.com.store.order.service.model.OrderModelResponse;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class OrderServiceImpl  implements OrderService {

    @Autowired
    private MongoOrderDomainRepository orderRepository;

    private OrderMapper orderMapper = Mappers.getMapper(OrderMapper.class);

    @Override
    public OrderModelResponse createOrder(OrderModel orderModel) {
        OrderDomain orderDomain = getOrderDomain(orderModel).createOrder();
        OrderDomain save = this.orderRepository.save(orderDomain);
        return this.orderMapper.mapToResponse(save);
    }

    @Override
    public void updateOrder(OrderModel orderModel) {
        OrderDomain orderDomain = getOrderDomain(orderModel).updateOrder();
        this.orderRepository.save(orderDomain);

    }

    @Override
    public void errorOrder(OrderModel request, OrderErrorStatus orderErrorStatus ) {
        OrderDomain orderDomain = getOrderDomain(request).error(orderErrorStatus);
        this.orderRepository.save(orderDomain);
    }

    @Override
    public OrderModelResponse getOrder(String id) {
        Optional<OrderDomain> orderDomain = this.orderRepository.findById(id);
        if (orderDomain.isPresent()){
            return this.orderMapper.mapToResponse(orderDomain.get());
        }
        throw new DomainException("Id invalido");
    }

    private OrderDomain getOrderDomain(OrderModel request){
        return  orderMapper.mapToDomain(request);
    }
}
