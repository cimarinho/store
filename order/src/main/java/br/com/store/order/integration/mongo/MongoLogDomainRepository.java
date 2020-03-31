package br.com.store.order.integration.mongo;

import br.com.store.order.domain.LogDomain;
import br.com.store.order.domain.repository.Repository;
import org.springframework.stereotype.Component;

@Component("LOGDOMAIN")
public class MongoLogDomainRepository implements Repository<LogDomain,Long>  {


    @Override
    public LogDomain save(LogDomain order) {
        System.out.println();
        return order;
    }
}
