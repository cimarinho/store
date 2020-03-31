package br.com.store.order.domain;

import br.com.store.order.application.request.OrderRequest;
import br.com.store.order.domain.exception.DomainException;
import br.com.store.order.domain.repository.Repository;
import br.com.store.order.domain.type.Status;
import lombok.Builder;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.List;

@Builder
@Getter
@Document(collection = "order")
public class OrderDomain {

    private Repository<OrderDomain, Long> repository;

    @Id
    public String id;

    private Long idOrder;
    private Double totalPrice;
    private LocalDate orderDate;
    private String idClient;
    private Status status;

    private List<OrderRequest.OrderItemRequest> items;


    public OrderDomain createOrder() {
        this.status = Status.CREATE;
        return repository.save(this);
    }

    public OrderDomain completeOrder() {
        validateStatus(Status.CREATE);
        this.status = Status.COMPLETE;
        return repository.save(this);
    }

    public OrderDomain paymentRefused() {
        validateStatus(Status.CREATE);
        return repository.save(this);
    }


    void validateStatus(Status receiveStatus) {
        if (receiveStatus.equals(this.status)) {
          throw new DomainException("Invalid Status.");
        }
    }


}
