package com.filatov.rooms;

import com.filatov.rooms.strategy.LoadBalancerStrategy;

import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LoadBalancerImp<T> implements LoadBalancer<T>, ResourcesSupplier<T>{

    private final Set<T> resources;
    private final Lock lock;
    private final LoadBalancerStrategy<T> loadBalancerStrategy;

    public LoadBalancerImp(LoadBalancerStrategy<T> loadBalancerStrategy){
        this.resources = new LinkedHashSet<>();
        this.lock = new ReentrantLock(true);
        this.loadBalancerStrategy = loadBalancerStrategy;
        loadBalancerStrategy.setResourcesSupplier(this);
    }

    @Override
    public void addResource(T res) {
        lock.lock();
        try {
            resources.add(res);
            loadBalancerStrategy.updateResources();
        } finally {
            lock.unlock();
        }
    }

    @Override
    public T next() {
        lock.lock();
        try {
            return loadBalancerStrategy.next();
        } finally {
            lock.unlock();
        }
    }

    @Override
    public Collection<T> getResources() {
        return resources;
    }

}
