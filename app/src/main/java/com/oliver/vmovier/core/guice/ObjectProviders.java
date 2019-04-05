package com.oliver.vmovier.core.guice;

import android.support.annotation.NonNull;
import android.util.ArrayMap;
import com.oliver.vmovier.core.guice.ObjectStore.ObjectHolder;

public class ObjectProviders {

    private static ArrayMap<Class<?>, NewInstanceFactory> mNewInstanceFactory;
    private static ObjectStore mObjectStore;

    static {
        mNewInstanceFactory = new ArrayMap<>(16);
        mObjectStore = new ObjectStore();
    }

    public static void register(Class<?> targetClass) {
        mNewInstanceFactory.put(targetClass, new DefaultFactory());
    }

    public static void register(Class<?> targetClass, NewInstanceFactory newInstanceFactory) {
        mNewInstanceFactory.put(targetClass, newInstanceFactory);
    }

    public static <T> T get(Class<T> targetClass) {
        if (mNewInstanceFactory.containsKey(targetClass)) {
            return mNewInstanceFactory.get(targetClass).create(targetClass);
        } else {
            throw new NotFoundTargetClassException(targetClass);
        }
    }

    public static <T> T getSingleton(Class<T> targetClass) {
        return get(targetClass, targetClass.getName() + "_singleton").getInstance();
    }

    @SuppressWarnings("unchecked")
    public static <T> ObjectRes<T> get(Class<T> targetClass, @NonNull String name) {
        ObjectHolder holder = mObjectStore.findInstance(targetClass, name);
        if (null == holder) {
            Object obj = get(targetClass);
            holder = new ObjectHolder(targetClass, name, obj);
            mObjectStore.put(holder);
        }
        return new ObjectRes<>(mObjectStore, holder);
    }

}
