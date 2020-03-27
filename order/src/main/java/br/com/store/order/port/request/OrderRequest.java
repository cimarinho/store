package br.com.store.order.port.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class OrderRequest {

    private Long idOrder;
    private BigDecimal totalPrice;
    private LocalDate orderDate;
    private String idClient;

    private List<OrderItemRequest> items;

    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class OrderItemRequest{
        private String productName;
        private BigDecimal price;
    }
}
