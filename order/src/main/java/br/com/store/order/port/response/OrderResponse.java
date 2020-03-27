package br.com.store.order.port.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class OrderResponse {

    private Long idOrder;
    private BigDecimal totalPrice;
    private LocalDate orderDate;
    private String idClient;
    private String status;

    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class OrderItemRequest {
        private String productName;
        private BigDecimal price;
    }
}
