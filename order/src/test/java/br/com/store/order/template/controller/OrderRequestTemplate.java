package br.com.store.order.template.controller;

import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;
import br.com.six2six.fixturefactory.loader.TemplateLoader;
import br.com.store.order.application.controller.request.OrderRequest;

import java.time.LocalDate;

public class OrderRequestTemplate implements TemplateLoader {

    public static final String ORDER_REQUEST_VALID = "ORDER_REQUEST_VALID";
    public static final String ORDER_REQUEST_INVALID = "ORDER_REQUEST_INVALID";

    @Override
    public void load() {
        Fixture.of(OrderRequest.class).addTemplate(ORDER_REQUEST_VALID, new Rule() {{
            add("idClient", "21");
            add("idOrder", "1");
            add("orderDate", LocalDate.now());
            add("totalPrice", 2.1);
            add("items", has(1).of(OrderRequest.OrderItemRequest.class, OrderItemRequestTemplate.ORDER_ITEM_REQUEST));
        }}).addTemplate(ORDER_REQUEST_INVALID, new Rule() {{
            add("idClient", "1");
            add("idOrder", "1");
            add("orderDate", LocalDate.now());
            add("totalPrice", 2.1);
            add("items", has(1).of(OrderRequest.OrderItemRequest.class, OrderItemRequestTemplate.ORDER_ITEM_REQUEST_INVALID));
        }});
    }
}

