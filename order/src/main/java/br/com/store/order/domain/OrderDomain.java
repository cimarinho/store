package br.com.store.order.domain;

import br.com.store.order.adapter.Repository;
import br.com.store.order.adapter.mongo.MongoRepository;
import br.com.store.order.domain.exception.DomainException;
import br.com.store.order.domain.type.Status;
import br.com.store.order.port.request.OrderRequest;
import lombok.Builder;
import lombok.Getter;
import org.springframework.core.annotation.Order;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Builder
@Getter
public class OrderDomain {

    private Repository<OrderDomain, Long> repository;

    private Long idOrder;
    private BigDecimal totalPrice;
    private LocalDate orderDate;
    private String idClient;
    private Status status;

    private List<OrderRequest.OrderItemRequest> items;

    public OrderDomain createOrder(OrderDomain order) {
        return repository.save(order);
    }

    public OrderDomain complete(OrderDomain order) {
        validateStatus();
        return repository.save(order);
    }

    void validateStatus() {
        if (Status.CREATE.equals(status)) {
            throw new DomainException("Status change is only allowed in accept create.");
        }
    }


}
