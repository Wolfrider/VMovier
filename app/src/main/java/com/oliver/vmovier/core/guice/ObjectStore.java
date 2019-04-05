package com.oliver.vmovier.core.guice;

import java.util.LinkedList;

import android.text.TextUtils;

public class ObjectStore {

    static class ObjectHolder {

        Class<?> mTargetClass;

        String mName;

        Object mInstance;

        ObjectHolder(Class<?> target, String name, Object object) {
            mName = name;
            mTargetClass = target;
            mInstance = object;
        }
    }

    private LinkedList<ObjectHolder> mObjectHolders;

    public ObjectStore() {
        mObjectHolders = new LinkedList<>();
    }

    public ObjectHolder findInstance(Class<?> target, String name) {
        for (ObjectHolder holder: mObjectHolders) {
            if (target.equals(holder.mTargetClass) && TextUtils.equals(name, holder.mName)) {
                return holder;
            }
        }
        return null;
    }

    public void put(ObjectHolder holder) {
        mObjectHolders.add(holder);
    }

    public void remove(ObjectHolder holder) {
        mObjectHolders.remove(holder);
    }

}
