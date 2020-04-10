package br.com.store.order.application.controller.interfaces;

import br.com.store.order.application.controller.OrderRequetController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

public interface AbstractController <T extends AbstractResponse> {

    default ResponseEntity getOk(T t){
        t.add(linkTo(methodOn(OrderRequetController.class).order(t.getId())).withSelfRel());
        return new ResponseEntity<>(t, HttpStatus.OK);
    }

}
