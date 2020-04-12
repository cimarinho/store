package br.com.store.order.application.controller.response;

import br.com.store.order.application.controller.interfaces.AbstractResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderResponse extends AbstractResponse {

    private String id;
    private String idClient;
    private Long idOrder;
    private LocalDate orderDate;
    private Double totalPrice;
    private String status;
    private List<OrderResponse.OrderItemResponse> items;

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class OrderItemResponse {

        private String productName;
        private Double price;
    }
}
