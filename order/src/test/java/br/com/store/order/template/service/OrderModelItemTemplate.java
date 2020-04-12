package br.com.store.order.template.service;

import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;
import br.com.six2six.fixturefactory.loader.TemplateLoader;
import br.com.store.order.service.model.OrderModel;

public class OrderModelItemTemplate implements TemplateLoader {

    public static final String ORDER_MODEL_ITEM = "ORDER_MODEL_ITEM";

    @Override
    public void load() {
        Fixture.of(OrderModel.OrderModelItem.class).addTemplate(ORDER_MODEL_ITEM, new Rule() {{
            add("productName", "Jogo");
            add("price", 50.10);
        }});
    }
}


