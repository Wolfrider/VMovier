package com.oliver.vmovier.core.guice;

import com.oliver.vmovier.core.guice.ObjectStore.ObjectHolder;

public class ObjectRes<T> implements AutoCloseable {

    private ObjectStore mObjectStore;
    private ObjectHolder mObjectHolder;

    ObjectRes(ObjectStore store, ObjectHolder holder) {
        mObjectStore = store;
        mObjectHolder = holder;
    }

    @SuppressWarnings("unchecked")
    public T getInstance() {
        return (T) mObjectHolder.mInstance;
    }

    @Override
    public void close() {
        mObjectStore.remove(mObjectHolder);
        mObjectStore = null;
        mObjectHolder = null;
    }
}
