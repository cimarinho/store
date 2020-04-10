package br.com.store.order.application.controller.interfaces;

import org.springframework.hateoas.RepresentationModel;

public abstract class AbstractResponse extends RepresentationModel {

    public abstract String getId();

}
