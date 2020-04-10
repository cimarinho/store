package br.com.store.order.application.controller.response;

import br.com.store.order.application.controller.interfaces.AbstractResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class OrderCreateResponse extends AbstractResponse {

    private String id;
}
