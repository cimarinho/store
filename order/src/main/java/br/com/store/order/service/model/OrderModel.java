package br.com.store.order.service.model;

import lombok.Builder;
import lombok.Getter;
import java.time.LocalDate;
import java.util.List;

@Getter
@Builder
public class OrderModel {
    private String id;
    private String idClient;
    private Long idOrder;
    private LocalDate orderDate;
    private Double totalPrice;
    private List<OrderModelItem> items;

    @Getter
    @Builder
    public static class OrderModelItem {

        private String productName;
        private Double price;
    }
}
