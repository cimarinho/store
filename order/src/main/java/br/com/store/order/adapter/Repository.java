package br.com.store.order.adapter;

public interface Repository <T ,  R> {

    T save (T t);

    T update (T t);

    T delete (R r);

    T findById(R r);
}
