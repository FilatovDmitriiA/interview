package com.filatov.rooms;

import java.util.Collection;

public interface ResourcesSupplier<T> {

    Collection<T> getResources();

}
