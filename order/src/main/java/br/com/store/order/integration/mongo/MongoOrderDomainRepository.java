package br.com.store.order.integration.mongo;

import br.com.store.order.domain.OrderDomain;
import br.com.store.order.domain.repository.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;

@Component("ORDERDOMAIN")
public class MongoOrderDomainRepository implements Repository <OrderDomain,Long> {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public OrderDomain save(OrderDomain orderDomain) {
        mongoTemplate.save(orderDomain);
        return orderDomain;
    }
}
