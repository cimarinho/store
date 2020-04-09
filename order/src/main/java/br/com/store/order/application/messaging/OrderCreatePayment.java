package br.com.store.order.application.messaging;

import br.com.store.order.application.messaging.interfaces.OrderInput;
import br.com.store.order.application.request.OrderRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.SendTo;

@Slf4j
//@EnableBinding({OrderInput.class})
public class OrderCreatePayment {

//    @Autowired
//    private OrderService orderService;

 //   @StreamListener(OrderInput.COMPLETE_PAYMENT)
  //  @SendTo(OrderInput.ORDER_LOG)
  //  public OrderRequest receive(OrderRequest orderRequest, @Headers MessageHeaders headers) {
      public void receive(OrderRequest orderRequest, @Headers MessageHeaders headers) {
        log.info("CREATE_PAYMNENT correlation_id["+headers.get("correlation_id")+"");
        orderRequest.setIdClient("22");
 //       return orderRequest;
    }
}
