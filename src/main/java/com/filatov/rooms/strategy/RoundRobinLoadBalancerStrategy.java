package com.filatov.rooms.strategy;

import java.util.Iterator;

public class RoundRobinLoadBalancerStrategy<T> extends LoadBalancerStrategy<T> {

    private Iterator<T> iterator;
    private int position;

    @Override
    public void updateResources() {
        updateIterator();
    }

    @Override
    public T next() {
        recreateIterator();
        if (iterator.hasNext()) {
            position++;
            return iterator.next();
        }
        throw new RuntimeException("LoadBalance empty");
    }

    private void updateIterator() {
        iterator = getResources().iterator();
        for (int i = 0; i < position; i++) {
            iterator.next();
        }
    }

    private void recreateIterator() {
        if (iterator == null || getResources().size() == position) {
            iterator = getResources().iterator();
        }
    }

}