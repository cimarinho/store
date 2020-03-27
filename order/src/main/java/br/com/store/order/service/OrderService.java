package br.com.store.order.service;

import br.com.store.order.adapter.Repository;
import br.com.store.order.domain.OrderDomain;
import br.com.store.order.port.request.OrderRequest;
import br.com.store.order.port.response.OrderResponse;
import br.com.store.order.service.mapper.OrderMapper;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

//    @Autowired
    private Repository<OrderDomain, Long> repository;

    private OrderMapper orderMapper = Mappers.getMapper(OrderMapper.class);


    public OrderResponse createOrder(OrderRequest request){
        OrderDomain orderDomain = orderMapper.mapToDomain(request, repository);
        orderDomain.createOrder();
        OrderResponse response = orderMapper.mapToResponse(orderDomain);
        return response;
    }

    public void changeStatus(OrderDomain order){

    }

}
