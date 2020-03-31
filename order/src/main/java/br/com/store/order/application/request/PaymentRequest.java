package br.com.store.order.application.request;

import lombok.Getter;
import lombok.ToString;

import java.io.Serializable;

@Getter
@ToString
public class PaymentRequest implements Serializable {

    private Long idPayment;
    private String status;
    private OrderRequest order;
}
