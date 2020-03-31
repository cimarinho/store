package br.com.store.order.application.request;

import lombok.Getter;
import lombok.ToString;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@ToString
@Getter
public class OrderRequest implements Serializable {

    private Long idOrder;
    private Double totalPrice;
    private LocalDate orderDate;
    private String idClient;
    private List<OrderItemRequest> items;

    @ToString
    @Getter
    public static class OrderItemRequest implements Serializable{
        private String productName;
        private Double price;
    }
}
