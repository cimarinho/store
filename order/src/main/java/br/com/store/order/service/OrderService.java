package br.com.store.order.service;

import br.com.store.order.application.request.OrderRequest;
import br.com.store.order.application.request.OrderResponse;

public interface OrderService {

    OrderResponse createOrder(OrderRequest request);

    OrderResponse completeOrder(OrderRequest request);

    OrderResponse paymentRefused(OrderRequest request);
}
