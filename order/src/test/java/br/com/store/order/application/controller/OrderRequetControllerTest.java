package br.com.store.order.application.controller;

import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.loader.FixtureFactoryLoader;
import br.com.store.order.application.controller.request.OrderRequest;
import br.com.store.order.application.messaging.OrderReceiveCreate;
import br.com.store.order.application.messaging.interfaces.OrderInput;
import br.com.store.order.service.OrderService;
import br.com.store.order.service.model.OrderModelResponse;
import br.com.store.order.template.controller.OrderRequestTemplate;
import br.com.store.order.template.service.OrderModelResponseTemplate;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.messaging.MessageChannel;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class OrderRequetControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean(name = OrderInput.RECEIVE)
    private MessageChannel messageChannel;

    @MockBean
    private OrderReceiveCreate orderReceiveCreate;

    @MockBean
    private OrderService orderService;

    @Before
    public void setup() {
        FixtureFactoryLoader.loadTemplates("br.com.store.order.template");
    }

    @Test
    public void create() throws Exception {
        OrderRequest orderRequest = Fixture.from(OrderRequest.class).gimme(OrderRequestTemplate.ORDER_REQUEST_VALID);
        OrderModelResponse orderModelResponse = Fixture.from(OrderModelResponse.class).gimme(OrderModelResponseTemplate.ORDER_MODEL_RESPONSE_VALID);
        when(this.orderService.getOrder(any())).thenReturn(orderModelResponse);
        String json = getJson(orderRequest);
        mockMvc.perform(post("/")
                .content(json)
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", notNullValue()));
    }

    @Test
    public void create_error_validation() throws Exception {
        OrderRequest orderRequest = new OrderRequest();
        String json = getJson(orderRequest);
        mockMvc.perform(post("/")
                .content(json)
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.errors", hasItem("idClient: idclient é obrigatório")))
                .andExpect(jsonPath("$.errors", hasItem("totalPrice: totalPrice é obrigatório")))
                .andExpect(jsonPath("$.errors", hasItem("orderDate: orderDate é obrigatório")))
                .andExpect(jsonPath("$.errors", hasItem("idOrder: idOrder é obrigatório")))
                .andExpect(jsonPath("$.errors", hasItem("items: items é obrigatorio")));
    }

    @Test
    public void create_error_validation_items() throws Exception {
        OrderRequest orderRequest = Fixture.from(OrderRequest.class).gimme(OrderRequestTemplate.ORDER_REQUEST_INVALID);
        String json = getJson(orderRequest);
        mockMvc.perform(post("/")
                .content(json)
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.errors", hasItem("items[0].productName: productName é obrigatório")))
                .andExpect(jsonPath("$.errors", hasItem("items[0].price: must be greater than or equal to 1")));
    }

    @Test
    public void get_test_mock() throws Exception {
        OrderModelResponse orderModelResponse = Fixture.from(OrderModelResponse.class).gimme(OrderModelResponseTemplate.ORDER_MODEL_RESPONSE_VALID);
        when(this.orderService.getOrder(any())).thenReturn(orderModelResponse);
        this.mockMvc.perform(get("/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", notNullValue()))
                .andExpect(jsonPath("$.orderDate", is(orderModelResponse.getOrderDate().toString())))
                .andExpect(jsonPath("$.status", is(orderModelResponse.getStatus())))
                .andExpect(jsonPath("$.idClient", is(orderModelResponse.getIdClient())))
                .andExpect(jsonPath("$.totalPrice", is(orderModelResponse.getTotalPrice())))
                .andExpect(jsonPath("$.items.[*].productName", containsInAnyOrder(orderModelResponse.getItems().get(0).getProductName().toString())))
                .andExpect(jsonPath("$.items.[*].price", containsInAnyOrder(orderModelResponse.getItems().get(0).getPrice())));
    }

    String getJson(OrderRequest request) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper().setSerializationInclusion(JsonInclude.Include.NON_NULL).registerModule(new JavaTimeModule());
        return objectMapper.writeValueAsString(request);
    }
}
