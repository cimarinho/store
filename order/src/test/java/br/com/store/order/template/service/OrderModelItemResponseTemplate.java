package br.com.store.order.template.service;

import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;
import br.com.six2six.fixturefactory.loader.TemplateLoader;
import br.com.store.order.service.model.OrderModelResponse;

public class OrderModelItemResponseTemplate implements TemplateLoader {

    public static final String ORDER_MODEL_ITEM_RESPONSE = "ORDER_MODEL_ITEM_RESPONSE";

    @Override
    public void load() {
        Fixture.of(OrderModelResponse.OrderModelItemResponse.class).addTemplate(ORDER_MODEL_ITEM_RESPONSE, new Rule() {{
            add("productName", "Jogo");
            add("price", 50.10);
        }});
    }


}
