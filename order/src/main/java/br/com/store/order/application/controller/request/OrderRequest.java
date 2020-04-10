package br.com.store.order.application.controller.request;

import lombok.Getter;

import java.time.LocalDate;
import java.util.List;

@Getter
public class OrderRequest {

    private String idClient;
    private Long idOrder;
    private LocalDate orderDate;
    private Double totalPrice;
    private List<OrderItemRequest> items;

    @Getter
    public static class OrderItemRequest {

        private String productName;
        private Double price;
    }
}
