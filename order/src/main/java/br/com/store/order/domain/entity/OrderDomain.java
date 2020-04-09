package br.com.store.order.domain.entity;

import br.com.store.order.config.exception.DomainException;
import br.com.store.order.domain.entity.type.Status;
import lombok.Builder;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;
import java.util.List;

@Builder
@Getter
@Document(collection = "order")
public class OrderDomain {

    @Id
    public String id;

    private Long idOrder;
    private Double totalPrice;
    private String orderDate;
    private String idClient;
    @NotNull
    private Status status;

    private List<OrderItemDomain> items;


    public OrderDomain createOrder() {
 //       this.status = Status.CREATE;
        return this;
    }

    public OrderDomain completeOrder() {
        validateStatus(Status.CREATE);
        this.status = Status.COMPLETE;
        return this;
    }

    public OrderDomain paymentRefused() {
        validateStatus(Status.ERROR_PAYMENT);
        return this;
    }

    void validateStatus(Status receiveStatus) {
        if (receiveStatus.equals(this.status)) {
          throw new DomainException("Invalid Status.");
        }
    }


}
