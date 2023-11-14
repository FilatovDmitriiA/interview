package com.filatov.rooms.strategy;

import com.filatov.rooms.ResourcesSupplier;

import java.util.Collection;

public abstract class LoadBalancerStrategy<T> {

    private ResourcesSupplier<T> resourcesSupplier;

    abstract public void updateResources();

    abstract public T next();

    public void setResourcesSupplier(ResourcesSupplier<T> resSupp) {
        this.resourcesSupplier = resSupp;
    }

    protected Collection<T> getResources(){
        if (resourcesSupplier == null) {
            throw new RuntimeException("No resourcesSupplier");
        }
        return resourcesSupplier.getResources();
    }

}
