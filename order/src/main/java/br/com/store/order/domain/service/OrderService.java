package br.com.store.order.domain.service;

import br.com.store.order.application.request.OrderRequest;
import br.com.store.order.application.response.OrderResponse;

public interface OrderService {

    OrderResponse createOrder(OrderRequest request);
}
