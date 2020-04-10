package br.com.store.order.application.controller.response;

import br.com.store.order.service.model.OrderModelResponse;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Builder
public class OrderResponse {

    private String id;
    private String idClient;
    private Long idOrder;
    private LocalDate orderDate;
    private Double totalPrice;
    private String status;
    private List<OrderModelResponse.OrderModelItemResponse> items;

    @Getter
    @Builder
    public static class OrderModelItemResponse {

        private String productName;
        private Double price;
    }
}
