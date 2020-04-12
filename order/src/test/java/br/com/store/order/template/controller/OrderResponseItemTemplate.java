package br.com.store.order.template.controller;

import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;
import br.com.six2six.fixturefactory.loader.TemplateLoader;
import br.com.store.order.application.controller.response.OrderResponse;

public class OrderResponseItemTemplate implements TemplateLoader {

    public static final String ORDER_ITEM_RESPONSE = "ORDER_ITEM_RESPONSE";

    @Override
    public void load() {
        Fixture.of(OrderResponse.OrderItemResponse.class).addTemplate(ORDER_ITEM_RESPONSE, new Rule() {{
            add("productName", "Jogo");
            add("price", 50.10);
        }});
    }


}
