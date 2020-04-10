package br.com.store.order.service;

import br.com.store.order.domain.entity.type.OrderErrorStatus;
import br.com.store.order.service.model.OrderModel;
import br.com.store.order.service.model.OrderModelResponse;

public interface OrderService {

    OrderModelResponse createOrder(OrderModel request);

    void updateOrder(OrderModel request);

    void errorOrder(OrderModel request, OrderErrorStatus orderErrorStatus);

    OrderModelResponse getOrder(String id);
}
