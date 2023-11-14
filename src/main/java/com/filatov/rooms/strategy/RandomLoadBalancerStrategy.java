package com.filatov.rooms.strategy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class RandomLoadBalancerStrategy<T> extends LoadBalancerStrategy<T> {

    @Override
    public void updateResources() {

    }

    @Override
    public T next() {
        List<T> currentResources = new ArrayList<>(getResources());
        Collections.shuffle(currentResources);
        Iterator<T> iterator = currentResources.iterator();
        if (iterator.hasNext()) {
            return iterator.next();
        }
        throw new RuntimeException("LoadBalance empty");
    }

}