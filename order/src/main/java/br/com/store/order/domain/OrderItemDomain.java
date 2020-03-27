package br.com.store.order.domain;

import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
@Builder
public class OrderItemDomain {
    private String productName;
    private BigDecimal price;
}
