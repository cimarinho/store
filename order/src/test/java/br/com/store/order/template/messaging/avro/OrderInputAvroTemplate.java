package br.com.store.order.template.messaging.avro;

import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;
import br.com.six2six.fixturefactory.loader.TemplateLoader;
import br.com.store.order.application.avro.OrderInputAvro;
import br.com.store.order.application.avro.OrderInputItemAvro;

import java.time.LocalDate;

public class OrderInputAvroTemplate  implements TemplateLoader {

    public static final String ORDER_AVRO_VALID = "ORDER_AVRO_VALID";

    @Override
    public void load() {
        Fixture.of(OrderInputAvro.class).addTemplate(ORDER_AVRO_VALID, new Rule() {{
            add("id", "ec11e975-8524-44c2-aae3-76094d86b1d1");
            add("idClient", "21");
            add("idOrder", "1");
            add("orderDate", "2020-04-11");
            add("totalPrice", 2.1);
            add("status", "CREATE");
            add("items", has(1).of(OrderInputItemAvro.class, OrderInputItemAvroTemplate.ORDER_ITEM_AVRO));
        }});
    }
}
