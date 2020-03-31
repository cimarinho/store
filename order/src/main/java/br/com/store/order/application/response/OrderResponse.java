package br.com.store.order.application.response;

import lombok.Builder;
import lombok.Getter;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Getter
@Builder
public class OrderResponse {

    private Long idOrder;
    private BigDecimal totalPrice;
    private LocalDate orderDate;
    private String idClient;
    private String status;

    private List<OrderResponse.OrderItemRequest> items;

    @Getter
    @Builder
    public static class OrderItemRequest {
        private String productName;
        private BigDecimal price;
    }
}
