package br.com.store.order.domain.repository;

public interface Repository <T, R> {

    T save(T t);
}
