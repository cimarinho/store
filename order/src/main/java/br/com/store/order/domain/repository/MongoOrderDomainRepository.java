package br.com.store.order.domain.repository;

import br.com.store.order.domain.entity.OrderDomain;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.UUID;

public interface MongoOrderDomainRepository extends MongoRepository<OrderDomain, String>{


}
