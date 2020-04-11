package br.com.store.order.domain.entity;

import br.com.store.order.config.exception.DomainException;
import br.com.store.order.domain.entity.type.OrderErrorStatus;
import br.com.store.order.domain.entity.type.Status;
import lombok.Builder;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Builder
@Getter
@Document(collection = "order")
public class OrderDomain {

    @Id
    public String id;
    @NotNull
    private Long idOrder;
    @NotNull
    private Double totalPrice;
    @NotEmpty
    private String orderDate;
    @NotEmpty
    private String idClient;
    @NotEmpty
    private String status;

    private List<OrderItemDomain> items;

    public OrderDomain createOrder() {
        this.status = Status.CREATE.name();
        return this;
    }

    public OrderDomain updateOrder() {
        validateStatus(Status.CREATE);
        this.status = Status.COMPLETE.name();
        return this;
    }

    public OrderDomain error(OrderErrorStatus orderErrorStatus) {
        String valueError = orderErrorStatus.getError().name();
        this.status = Status.getStatus(valueError).name();;
        return this;
    }

    void validateStatus(Status receiveStatus) {
        if (receiveStatus.equals(this.status)) {
            throw new DomainException("Invalid Status.");
        }
    }
}
