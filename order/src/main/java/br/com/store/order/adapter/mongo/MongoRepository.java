package br.com.store.order.adapter.mongo;

import br.com.store.order.domain.OrderDomain;

public interface MongoRepository {

    OrderDomain save(OrderDomain order);
}
