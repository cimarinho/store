package br.com.store.order.application.controller.request;

import lombok.Getter;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;

@Getter
public class OrderRequest {

    @NotEmpty(message = "idclient é obrigatório")
    private String idClient;
    @NotNull(message = "idOrder é obrigatório")
    @Min(value = 1)
    private Long idOrder;
    @NotNull(message = "orderDate é obrigatório")
    private LocalDate orderDate;
    @NotNull(message = "totalPrice é obrigatório")
    @Min(value = 1)
    private Double totalPrice;
    @Valid
    private List<OrderItemRequest> items;

    @Getter
    public static class OrderItemRequest {
        @NotEmpty(message = "productName é obrigatório")
        private String productName;
        @NotNull(message = "price é obrigatório")
        @Min(value = 1)
        private Double price;
    }
}
