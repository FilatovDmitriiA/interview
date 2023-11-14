package com.filatov.rooms;

public interface LoadBalancer<T> {

    void addResource(T res);

    T next();

}
