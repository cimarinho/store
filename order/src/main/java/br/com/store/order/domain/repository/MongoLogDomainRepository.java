package br.com.store.order.domain.repository;

import br.com.store.order.domain.entity.LogDomain;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.UUID;

public interface MongoLogDomainRepository extends MongoRepository<LogDomain, UUID> {
}
