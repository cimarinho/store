package br.com.store.order.domain.entity;

import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Getter
@Builder
public class OrderItemDomain {
    @NotEmpty
    private String productName;
    @NotNull
    private BigDecimal price;
}
