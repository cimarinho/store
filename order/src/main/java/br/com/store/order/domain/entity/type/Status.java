package br.com.store.order.domain.entity.type;

import br.com.store.order.config.exception.DomainException;

import java.util.Arrays;

public enum Status {

    CREATE, APPROVED_PAYMENT, APPROVED_SHIPPING, ERROR_ORDER, COMPLETE, ERROR_SHIPPING, ERROR_PAYMENT;

    public static Status getStatus(String value){
        return Arrays.stream(Status.values()).filter(it -> it.equals(value)).findAny().orElseThrow(() -> new DomainException("Status invalidate"));
    }

}
