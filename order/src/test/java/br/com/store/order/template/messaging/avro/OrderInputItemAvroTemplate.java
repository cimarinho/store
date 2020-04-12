package br.com.store.order.template.messaging.avro;

import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;
import br.com.six2six.fixturefactory.loader.TemplateLoader;
import br.com.store.order.application.avro.OrderInputItemAvro;

public class OrderInputItemAvroTemplate  implements TemplateLoader {

    public static final String ORDER_ITEM_AVRO = "ORDER_ITEM_AVRO";

    @Override
    public void load() {
        Fixture.of(OrderInputItemAvro.class).addTemplate(ORDER_ITEM_AVRO, new Rule() {{
            add("productName", "Jogo");
            add("price", 50.10);
        }});
    }


}
