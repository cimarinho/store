package br.com.store.order.template.controller;

import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;
import br.com.six2six.fixturefactory.loader.TemplateLoader;
import br.com.store.order.application.controller.request.OrderRequest;

public class OrderItemRequestTemplate implements TemplateLoader {

    public static final String ORDER_ITEM_REQUEST = "ORDER_ITEM_REQUEST";
    public static final String ORDER_ITEM_REQUEST_INVALID = "ORDER_ITEM_REQUEST_INVALID";

    @Override
    public void load() {
        Fixture.of(OrderRequest.OrderItemRequest.class).addTemplate(ORDER_ITEM_REQUEST, new Rule() {{
            add("productName", "Jogo");
            add("price", 50.10);
        }}).addTemplate(ORDER_ITEM_REQUEST_INVALID, new Rule() {{
            add("productName", "");
            add("price", 0.0);
        }});
    }


}
