package br.com.store.order.integration.mongo;

import br.com.store.order.domain.OrderDomain;
import br.com.store.order.domain.repository.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Component;

@Qualifier(value = "ORDERDOMAIN")
@Component
public class MongoOrderDomainRepository implements Repository <OrderDomain,Long> {

    @Autowired
    MongoTemplate mongoTemplate;

    @Override
    public OrderDomain save(OrderDomain orderDomain) {
        mongoTemplate.save(orderDomain);
        return orderDomain;
    }
}
