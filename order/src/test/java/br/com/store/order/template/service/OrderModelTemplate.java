package br.com.store.order.template.service;

import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;
import br.com.six2six.fixturefactory.loader.TemplateLoader;
import br.com.store.order.service.model.OrderModel;
import br.com.store.order.service.model.OrderModelResponse;

import java.time.LocalDate;
import java.util.List;

public class OrderModelTemplate  implements TemplateLoader {

    public static final String ORDER_MODEL_VALID = "ORDER_MODEL_VALID";

    @Override
    public void load() {
        Fixture.of(OrderModel.class).addTemplate(ORDER_MODEL_VALID, new Rule() {{
            add("id", "ec11e975-8524-44c2-aae3-76094d86b1d1");
            add("idClient", "21");
            add("idOrder", "1");
            add("orderDate", LocalDate.now());
            add("totalPrice", 2.1);
            add("status", "CREATE");
            add("items", has(1).of(OrderModel.OrderModelItem.class, OrderModelItemTemplate.ORDER_MODEL_ITEM));
        }}) ;
    }
}


