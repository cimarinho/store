package br.com.store.order.domain.entity.type;

import br.com.store.order.config.exception.DomainException;

import java.util.Arrays;

public enum OrderErrorStatus {
    ORDER("ERROR_ORDER"), PAYMENT("ERROR_PAYMENT"), SHIPPING("ERROR_SHIPPING");

    private String error;

    OrderErrorStatus(String error) {
        this.error = error;
    }

    public OrderErrorStatus getError() {
        return Arrays.stream(OrderErrorStatus.values()).filter(it -> it.equals(this)).findAny().orElseThrow(() -> new DomainException("Status invalidate"));
    }
}
