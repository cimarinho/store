package br.com.store.order.template.controller;

import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;
import br.com.six2six.fixturefactory.loader.TemplateLoader;
import br.com.store.order.application.controller.response.OrderResponse;

import java.time.LocalDate;

public class OrderResponseTemplate implements TemplateLoader {

    public static final String ORDER_RESPONSE_VALID = "ORDER_RESPONSE_VALID";

    @Override
    public void load() {
        Fixture.of(OrderResponse.class).addTemplate(ORDER_RESPONSE_VALID, new Rule() {{
            add("id", "ec11e975-8524-44c2-aae3-76094d86b1d1");
            add("idClient", "21");
            add("idOrder", "1");
            add("orderDate", LocalDate.now());
            add("totalPrice", 2.1);
            add("status", "CREATE");
            add("items", has(1).of(OrderResponse.OrderItemResponse.class, OrderResponseItemTemplate.ORDER_ITEM_RESPONSE));
        }});
    }
}
