package br.com.store.order.domain;

import br.com.store.order.domain.mapper.OrderMapper;
import br.com.store.order.domain.repository.Repository;
import br.com.store.order.domain.service.OrderService;
import br.com.store.order.application.request.OrderRequest;
import br.com.store.order.application.response.OrderResponse;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService {

    @Qualifier(value = "ORDERDOMAIN")
    @Autowired
    private Repository<OrderDomain, Long> orderRepository;


    @Qualifier(value = "LOGDOMAIN")
    @Autowired
    private Repository<LogDomain, Long> logRepository;

    private OrderMapper orderMapper = Mappers.getMapper(OrderMapper.class);

    public OrderResponse createOrder(OrderRequest request) {
        OrderDomain orderDomain = getOrderDomain(request).createOrder();
        return orderMapper.mapToResponse(orderDomain);
    }

    public OrderResponse completeOrder(OrderRequest request) {
        OrderDomain orderDomain = getOrderDomain(request).completeOrder();
        return orderMapper.mapToResponse(orderDomain);

    }

    public OrderResponse paymentRefused(OrderRequest request) {
        OrderDomain orderDomain = getOrderDomain(request).completeOrder();
        LogDomain logDomain = LogDomain.builder().idOrder(orderDomain.getIdOrder()).error("paymentRefused").build();
        this.logRepository.save(logDomain);
        return orderMapper.mapToResponse(orderDomain);
    }

    OrderDomain getOrderDomain(OrderRequest request){
        return  orderMapper.mapToDomain(request, orderRepository);
    }

}
